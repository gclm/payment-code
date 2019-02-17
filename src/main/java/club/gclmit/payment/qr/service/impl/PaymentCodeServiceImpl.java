package club.gclmit.payment.qr.service.impl;

import club.gclmit.payment.qr.dao.PaymentCodeDao;
import club.gclmit.payment.qr.model.entity.PaymentCode;
import club.gclmit.payment.qr.model.enums.QrCodeType;
import club.gclmit.payment.qr.model.enums.TitileCode;
import club.gclmit.payment.qr.service.PaymentCodeService;
import club.gclmit.payment.qr.utils.IdWorker;
import club.gclmit.payment.qr.utils.ObjectIsNullUtils;
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
    private ObjectIsNullUtils objectIsNullUtils;

    @Override
    public String[] parsePaymentCode(File file,String userId) throws IOException, NotFoundException, NoSuchFieldException, IllegalAccessException {
        String titleType = "qq";

        PaymentCode paymentCode = paymentCodeDao.findPaymentCodeByUserId(userId);

        if (StringUtils.isEmpty(paymentCode)){
            paymentCode = new PaymentCode();
            paymentCode.setId(idWorker.nextId()+"");
        }

        paymentCode.setUserId(userId);
        String result = qrCodeUtils.parseQrCode(file, QrCodeType.LOCAL_QR_CODE.getDesc());

        if (result.indexOf(TitileCode.ALI_JUDGE_CODE.getDesc()) >= 0 ){
            titleType = "alipay";
            if (objectIsNullUtils.objectSingleFieldIsNull(paymentCode,"alipayPaymentCode")){
                log.info("执行 setAlipayPaymentCode .....");
                paymentCode.setAlipayPaymentCode(result);
            }
        } else if (result.indexOf(TitileCode.WEIXIN_JUDGE_CODE.getDesc()) >= 0){
            titleType = "weixin";
            if (objectIsNullUtils.objectSingleFieldIsNull(paymentCode,"weixinPaymentCode")){
                log.info("执行 setWeixinPaymentCode .....");
                paymentCode.setWeixinPaymentCode(result);
            }
        } else if (objectIsNullUtils.objectSingleFieldIsNull(paymentCode,"qqPaymentCode")){
            log.info("执行 setQqPaymentCode .....");
            paymentCode.setQqPaymentCode(result);
        }

        paymentCodeDao.save(paymentCode);
        return new String[]{titleType,result};
    }

    @Override
    public String generatePaymentCode(String hostUrl, String aliPay, String qqPay, String weixinPay, String username) throws IOException, WriterException {

        PaymentCode paymentCode = paymentCodeDao.findPaymentCodeByCompoundCode(aliPay,qqPay, weixinPay);

        log.info("使用三大付款码查询到paymentQr: "+ paymentCode);

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

        if (!objectIsNullUtils.isEmpty(paymentCode)){
            if (userAgentToString.indexOf(TitileCode.WEIXIN_PAY_CODE.getDesc()) >=0){
                paymentCodeToString = paymentCode.getWeixinPaymentCode();
            } else if (userAgentToString.indexOf(TitileCode.ALI_PAY_CODE.getDesc()) >=0){
                paymentCodeToString = paymentCode.getAlipayPaymentCode();
            } else {
                paymentCodeToString = paymentCode.getQqPaymentCode();
            }

            QrCodeUtils.QrCode qrCode = qrCodeUtils.instance();
            qrCode.setContent(paymentCodeToString);

            return  new String[]{qrCodeUtils.generateQrCode(qrCode),paymentCode.getUsername()};
        }
        return  new String[]{null};
    }
}
