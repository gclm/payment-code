package club.gclmit.payment.qr.utils;

import java.io.File;
import java.io.IOException;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.utils
 * @author: gclm
 * @date: 2019-02-16 11:42
 * @description:
 */
public class FileConversionUtilTest {

    public static void main(String[] args) throws IOException {

        String path =  "/Volumes/Downloads/XX/ZZZZ/123456.exe";

        FileConversionUtil fileConversionUtil = new FileConversionUtil();
        File file = fileConversionUtil.judgeFileExists(path);

        System.out.println(file.length());

    }
}
