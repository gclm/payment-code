package club.gclmit.payment.qr.service.impl;

import club.gclmit.payment.qr.dao.PaymentCodeDao;
import club.gclmit.payment.qr.model.entity.PaymentCode;
import club.gclmit.payment.qr.model.enums.QrCodeType;
import club.gclmit.payment.qr.model.enums.TitileCode;
import club.gclmit.payment.qr.service.PaymentCodeService;
import club.gclmit.payment.qr.utils.IdWorker;
import club.gclmit.payment.qr.utils.ObjectOperationUtils;
import club.gclmit.payment.qr.utils.QrCodeUtils;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.service.impl.impl
 * @author: gclm
 * @date: 2019-02-13 16:33
 * @description:
 */
@Slf4j
@Service
@Transactional
public class PaymentCodeServiceImpl implements PaymentCodeService {

    @Autowired
    private PaymentCodeDao paymentCodeDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private QrCodeUtils qrCodeUtils;

    @Autowired
    private ObjectOperationUtils objectOperationUtils;

    /**
     * 解析二维码
     * @details 孤城落寞 2019-02-18 08:18
     * @param file  二维码未见
     * @return java.lang.String[String,String]
     *  1. 类型：String 为解析出来的二维码信息
     *  2. 类型：String 二维码类型，是QQ、微信、还是支付宝
     */
//    @Override
//    public String[] parsePaymentCode(File file,String id) throws IOException, NotFoundException, NoSuchFieldException, IllegalAccessException {
//
//        if(!objectIsNullUtils.isEmpty(file)){
//
//            String titleType = "all";
//
////            PaymentCode paymentCode = paymentCodeDao.findPaymentCodeBySimpleondition(id);
////
////            if(StringUtils.isEmpty(paymentCode)){
////                 paymentCode = new PaymentCode();
////                 paymentCode.setId(idWorker.nextId()+"");
////            }
//
//            String result = qrCodeUtils.parseQrCode(file, QrCodeType.LOCAL_QR_CODE.getDesc());
//
//            PaymentCode paymentCode = paymentCodeDao.findPaymentCodeBySimpleondition(result);
//
//            if(StringUtils.isEmpty(paymentCode)){
//                 paymentCode = new PaymentCode();
//                 paymentCode.setId(idWorker.nextId()+"");
//            }
//
//            log.info("二维码信息："+result);
//
//            if (result.indexOf(TitileCode.ALI_JUDGE_CODE.getDesc()) >= 0 ){
//                titleType = "alipay";
//                if (objectIsNullUtils.objectSingleFieldIsNull(paymentCode,"alipayPaymentCode")){
//                    log.info("执行 setAlipayPaymentCode .....");
//                    paymentCode.setAlipayPaymentCode(result);
//                }
//            } else if (result.indexOf(TitileCode.WEIXIN_JUDGE_CODE.getDesc()) >= 0){
//                titleType = "weixin";
//                if (objectIsNullUtils.objectSingleFieldIsNull(paymentCode,"weixinPaymentCode")){
//                    log.info("执行 setWeixinPaymentCode .....");
//                    paymentCode.setWeixinPaymentCode(result);
//                }
//            } else if (result.indexOf(TitileCode.QQ_JUDGE_CODE.getDesc()) >= 0){
//                titleType = "qq";
//                if(objectIsNullUtils.objectSingleFieldIsNull(paymentCode,"qqPaymentCode")){
//                    log.info("执行 setQqPaymentCode .....");
//                    paymentCode.setQqPaymentCode(result);
//                }
//            } else {
//                if(objectIsNullUtils.objectSingleFieldIsNull(paymentCode,"paymentCode")){
//                    log.info("执行 setPaymentCode .....");
//                    paymentCode.setPaymentCode(result);
//                }
//            }
//
//            PaymentCode save = paymentCodeDao.save(paymentCode);
//
//            return new String[]{result,titleType,save.getId()};
//        }
//        return new String[]{null};
//    }

    @Override
    public String[] parsePaymentCode(File file,String id) throws IOException, NotFoundException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        if(!objectOperationUtils.isEmpty(file)){

            String titleType = null;

            PaymentCode paymentCode = null;

            String result = qrCodeUtils.parseQrCode(file, QrCodeType.LOCAL_QR_CODE.getDesc());
            log.info("二维码信息："+result);

            if(objectOperationUtils.isEmpty(id)){
                titleType = judgeCodeType(result);
                log.info("版本信息："+titleType);
                if ("alipayPaymentCode".equals(titleType)){
                    paymentCode = paymentCodeDao.findPaymentCodeBySimpleondition(result,"","");
                } else if ("weixinPaymentCode".equals(titleType)){
                    paymentCode = paymentCodeDao.findPaymentCodeBySimpleondition("","",result);
                } else if ("qqPaymentCode".equals(titleType)){
                    paymentCode = paymentCodeDao.findPaymentCodeBySimpleondition("",result,"");
                }
            } else {
                paymentCode = paymentCodeDao.findPaymentCodeById(id);
                titleType = judgeCodeType(result);
            }


            if(StringUtils.isEmpty(paymentCode)){
                paymentCode = new PaymentCode();
                paymentCode.setId(idWorker.nextId()+"");
            }
            paymentCode = setPaymentCodeField(titleType, paymentCode, result);
            log.info("paymentCode对象是否为空："+paymentCode);
            PaymentCode save = paymentCodeDao.save(paymentCode);

            return new String[]{result,titleType,save.getId()};
        }
        return new String[]{null};
    }

    @Override
    public String generatePaymentCode(String hostUrl, String aliPay, String qqPay, String weixinPay, String username) throws IOException, WriterException {

        PaymentCode paymentCode = paymentCodeDao.findPaymentCodeByCompoundCode(aliPay,qqPay, weixinPay);

        log.info("使用三大付款码查询到paymentQr: "+ paymentCode);

        if (StringUtils.isEmpty(paymentCode)){
        }

        if (!StringUtils.isEmpty(paymentCode)){

            String url = new StringBuilder(hostUrl).append("/pay?id=").append(paymentCode.getId()).toString();

            paymentCode.setPaymentCode(url);
            paymentCode.setUsername(username);

            paymentCodeDao.save(paymentCode);

            log.info("需要生成的二维码的URL为："+url);
            QrCodeUtils.QrCode qrCode = qrCodeUtils.instance();
            qrCode.setContent(url);
            return qrCodeUtils.generateQrCode(qrCode);
        }
        return null;
    }

    @Override
    public String[] scanPaymentCode(String userAgentToString, String id) throws IOException, WriterException {

        String paymentCodeToString = null;

        PaymentCode paymentCode = paymentCodeDao.findPaymentCodeById(id);

        String titleCode = "qq";

        if (!objectOperationUtils.isEmpty(paymentCode)){
            if (userAgentToString.indexOf(TitileCode.WEIXIN_PAY_CODE.getDesc()) >=0){
                titleCode = "wechat";
                paymentCodeToString = paymentCode.getWeixinPaymentCode();
            } else if (userAgentToString.indexOf(TitileCode.ALI_PAY_CODE.getDesc()) >=0){
                return new String[]{paymentCode.getAlipayPaymentCode(),paymentCode.getUsername(),"alipay"};
            } else {
                paymentCodeToString = paymentCode.getQqPaymentCode();
            }

            QrCodeUtils.QrCode qrCode = qrCodeUtils.instance();
            qrCode.setContent(paymentCodeToString);

            return  new String[]{qrCodeUtils.generateQrCode(qrCode),paymentCode.getUsername(),titleCode};
        }
        return  new String[]{null};
    }


    private PaymentCode setPaymentCodeField(String fieldName, PaymentCode paymentCode, String result) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        if(objectOperationUtils.objectSingleFieldIsNull(paymentCode,fieldName)){
            log.info("执行 "+fieldName);
            return (PaymentCode) objectOperationUtils.setObjectField(paymentCode, fieldName, result);
        }
        return paymentCode;
    }

    private String judgeCodeType(String result){
        String titleType = null;
        if (result.indexOf(TitileCode.ALI_JUDGE_CODE.getDesc()) >= 0 ){
            titleType = "alipayPaymentCode";
        } else if (result.indexOf(TitileCode.WEIXIN_JUDGE_CODE.getDesc()) >= 0){
            titleType = "weixinPaymentCode";
        } else if ( (result.indexOf(TitileCode.QQ_JUDGE_CODE.getDesc()) >= 0) || (result.indexOf(TitileCode.TIM_JUDGE_CODE.getDesc()) >= 0)){
            titleType = "qqPaymentCode";
        } else {
            titleType = "paymentCode";
        }
        return titleType;
    }
}
