package club.gclmit.payment.qr.other;

import club.gclmit.payment.qr.model.enums.TitileCode;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.other
 * @author: gclm
 * @date: 2019-02-18 13:37
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        String result = "http://vac.qq.com/wallet/qrcode.htm?m=tenpay&a=1&u=1719982754&ac=F841F5F321D1E476C9E79AC506FEA966F063DC3C6DACAD292EBF28C2507F4B26&n=孤城落寞&f=wallet";
        String titleType =null;
        if (result.indexOf(TitileCode.ALI_JUDGE_CODE.getDesc()) >= 0 ){
            titleType = "alipayPaymentCode";
        } else if (result.indexOf(TitileCode.WEIXIN_JUDGE_CODE.getDesc()) >= 0){
            titleType = "weixinPaymentCode";
        } else if (result.indexOf(TitileCode.QQ_JUDGE_CODE.getDesc()) >= 0 || result.indexOf(TitileCode.TIM_JUDGE_CODE.getDesc()) >= 0){
            titleType = "qqPaymentCode";
        } else {
            titleType = "paymentCode";
        }
        System.out.println(titleType);
    }
}
