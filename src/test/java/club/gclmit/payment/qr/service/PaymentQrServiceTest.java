package club.gclmit.payment.qr.service;

import club.gclmit.payment.qr.model.entity.PaymentCode;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.service
 * @author: gclm
 * @date: 2019-02-14 16:24
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentQrServiceTest {

    @Autowired
    private PaymentCodeService paymentCodeService;

    @Test
    public void parseQRCodeTest() throws NoSuchFieldException, IllegalAccessException, NotFoundException, IOException, InvocationTargetException, NoSuchMethodException {
        File file = new File("/Volumes/Downloads/XXXX/YYY/1096951025811722241.png");
        String[] strings = paymentCodeService.parsePaymentCode(file,"1097333658886475777");
        if(strings[0] != null){
            log.info("\nservice: "+strings[0]+" "+strings[1]+" "+strings[2]);
        }else{
            log.info("\nservice: "+strings[0]);
        }
    }

    @Test
    public void generatePaymentCodeTest() throws IOException, WriterException {
        String result = paymentCodeService.generatePaymentCode( "http://10.1.36.14:8080/qr", "https://qr.alipay.com/fkx05747m3aqx7jc14a88c0", "http://vac.qq.com/wallet/qrcode.htm?m=tenpay&a=1&u=1719982754&ac=F841F5F321D1E476C9E79AC506FEA966F063DC3C6DACAD292EBF28C2507F4B26&n=孤城落寞&f=wallet", "wxp://f2f0EbkTB_LDAiur_EQoKKjHz7h_UUHbJgt0","XXX");

        System.out.println(result);

    }
}
