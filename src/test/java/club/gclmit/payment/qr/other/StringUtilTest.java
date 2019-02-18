package club.gclmit.payment.qr.other;

import club.gclmit.payment.qr.model.entity.PaymentCode;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.other
 * @author: gclm
 * @date: 2019-02-18 10:00
 * @description:
 */
public class StringUtilTest {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
         String s = "qqPaymentCode";
//         String s1 = new StringBuilder(s.substring(0, 1).toUpperCase()).append(s.substring(1)).toString();
//        System.out.println("s:"+ s +" update: "+s1);


        PaymentCode paymentCode = new PaymentCode();
        paymentCode.setWeixinPaymentCode("12");
        paymentCode.setQqPaymentCode("123");
        paymentCode.setAlipayPaymentCode("1234");

        System.out.println("原对象："+paymentCode);

        Class<? extends PaymentCode> paymentCodeClass = paymentCode.getClass();


        Field field = paymentCodeClass.getDeclaredField(s.toString());

        field.setAccessible(true);

        char[] cs = s.toCharArray();
        cs[0]  -= 32;

        System.out.println("首字母大写： "+String.valueOf(cs));

        String fieldName = new StringBuilder("set").append(String.valueOf(cs)).toString();

        System.out.println("方法名："+fieldName);

        // 调用set方法
        Method method = paymentCodeClass.getDeclaredMethod(fieldName,field.getType());

        // 赋值
         method.invoke(paymentCode,"AA123");

        field.setAccessible(false);

        System.out.println("修改后的："+paymentCode);
    }
}
