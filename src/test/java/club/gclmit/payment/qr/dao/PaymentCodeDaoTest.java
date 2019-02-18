package club.gclmit.payment.qr.dao;

import club.gclmit.payment.qr.model.entity.PaymentCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.dao
 * @author: gclm
 * @date: 2019-02-14 16:15
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentCodeDaoTest {

    @Autowired
    private PaymentCodeDao paymentCodeDao;

    @Test
    public void  findPaymentQrByCompoundCodeTest(){

//        1095903529106804736	https://qr.alipay.com/fkx05747m3aqx7jc14a88c0	null	http://vac.qq.com/wallet/qrcode.htm?m=tenpay&a=1&u=1719982754&ac=F841F5F321D1E476C9E79AC506FEA966F063DC3C6DACAD292EBF28C2507F4B26&n=孤城落寞&f=wallet	12	wxp://f2f0EbkTB_LDAiur_EQoKKjHz7h_UUHbJgt0
        PaymentCode  paymentCode = paymentCodeDao.findPaymentCodeByCompoundCode("https://qr.alipay.com/fkx05747m3aqx7jc14a88c0", "http://vac.qq.com/wallet/qrcode.htm?m=tenpay&a=1&u=1719982754&ac=F841F5F321D1E476C9E79AC506FEA966F063DC3C6DACAD292EBF28C2507F4B26&n=孤城落寞&f=wallet", "wxp://f2f0EbkTB_LDAiur_EQoKKjHz7h_UUHbJgt0");

        System.out.println(paymentCode);
    }

    @Test
    public void  findPaymentCodeBySimpleonditionTest(){

//        1095903529106804736	https://qr.alipay.com/fkx05747m3aqx7jc14a88c0	null	http://vac.qq.com/wallet/qrcode.htm?m=tenpay&a=1&u=1719982754&ac=F841F5F321D1E476C9E79AC506FEA966F063DC3C6DACAD292EBF28C2507F4B26&n=孤城落寞&f=wallet	12	wxp://f2f0EbkTB_LDAiur_EQoKKjHz7h_UUHbJgt0
        PaymentCode  paymentCode = paymentCodeDao.findPaymentCodeBySimpleondition("","","wxp://f2f0EbkTB_LDAiur_EQoKKjHz7h_UUHbJgt0");

        System.out.println(paymentCode);
    }
}
