package club.gclmit.payment.qr.service;

import club.gclmit.payment.qr.model.entity.PaymentCode;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.service
 * @author: gclm
 * @date: 2019-02-13 16:31
 * @description:
 */
public interface PaymentCodeService {

    public String[] parsePaymentCode(File file,String id) throws IOException, NotFoundException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;

//    hostUrl, aliPay, qqPay, weixinPay
    public String generatePaymentCode(String hostUrl, String aliPay, String qqPay, String weixinPay, String username) throws IOException, WriterException;

    public String[] scanPaymentCode(String userAgentToString, String id) throws IOException, WriterException;
}
