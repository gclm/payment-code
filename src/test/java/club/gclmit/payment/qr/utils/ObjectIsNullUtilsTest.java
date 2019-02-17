package club.gclmit.payment.qr.utils;

import club.gclmit.payment.qr.model.entity.PaymentCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.utils
 * @author: gclm
 * @date: 2019-02-15 14:57
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectIsNullUtilsTest {

    @Autowired
    private ObjectIsNullUtils objectIsNullUtils;

    @Test
    public void objectSingleFieldIsNullTest() throws NoSuchFieldException, IllegalAccessException {
        PaymentCode paymentCode = new PaymentCode();
        paymentCode.setAlipayPaymentCode("https://qr.alipay.com/fkx05747m3aqx7jc14a88c0");

        System.out.println("PaymentCode 对象："+paymentCode);
        System.out.println("有参："+objectIsNullUtils.objectSingleFieldIsNull(paymentCode, "alipayPaymentCode"));
        System.out.println("空参："+objectIsNullUtils.objectSingleFieldIsNull(paymentCode, "userId"));
        System.out.println("空参："+objectIsNullUtils.objectSingleFieldIsNull(paymentCode, "qqPaymentCode"));
    }

    @Test
    public void isEmptyTest() {
        List<String> urls = Arrays.asList("", null, "https://qr.alipay.com/fkx05747m3aqx7jc14a88c0");

        urls.forEach(urlToString ->{
            try {
                URL url = new URL(urlToString);
                System.out.println(objectIsNullUtils.isEmpty(url));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

}
