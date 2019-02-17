package club.gclmit.payment.qr.utils;

import club.gclmit.payment.qr.model.enums.QrCodeType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.qr
 * @author: gclm
 * @date: 2019-02-13 15:43
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QrUtilTest {

    @Autowired
    private QrCodeUtils qrCodeUtils;

    @Test
    public void parseQrCodeTest() throws Exception {
        /**
         * 本地图片
         */
        File file = new File("/Volumes/Downloads/1096299306660532224.png");
        String result = qrCodeUtils.parseQrCode(file, QrCodeType.LOCAL_QR_CODE.getDesc());
        System.out.println(result);

        /**
         * 网络图片
         */
        String result1 = qrCodeUtils.parseQrCode("https://res.wx.qq.com/mpres/htmledition/images/mp_qrcode3a7b38.gif", QrCodeType.NET_QR_CODE.getDesc());

        System.out.println(result1);
    }

    @Test
    public  void generateQrCodeTest() throws IOException, WriterException {

        /**
         * 生成 Base64
         */
        QrCodeUtils.QrCode qrCode= qrCodeUtils.instance();
        qrCode.setContent("https://qr.alipay.com/fkx05747m3aqx7jc14a88c0");
        String result = qrCodeUtils.generateQrCode(qrCode);
        System.out.println("result:"+result+"===");

        /**
         * 生成文件版
         */
        QrCodeUtils.QrCode qrCodeFile = qrCodeUtils.instance();
        qrCodeFile.setContent("http://10.1.36.14:8080/qr/pay?id=1096606729992540160");
        qrCodeFile.setGenerateType("file");
        qrCodeFile.setGeneartePath("/Volumes/Downloads/XXXX/YYY");
        String qrCodeFilePath = qrCodeUtils.generateQrCode(qrCodeFile);
        System.out.println("生成二维码文件路径:"+qrCodeFilePath);

    }

}