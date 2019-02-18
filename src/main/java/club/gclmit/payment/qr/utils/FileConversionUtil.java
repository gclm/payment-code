package club.gclmit.payment.qr.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.utils
 * @author: gclm
 * @date: 2019-02-14 10:04
 * @description:
 */
@Slf4j
@Component
public class FileConversionUtil {

    /**
     *  MultipartFile 转 File
     * @details 孤城落寞 2019-02-14 10:16
     * @param multipartFile
     * @return java.io.File
     */
    public File multipartFileToFile(MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        String suffixName = filename.substring(filename.indexOf("."));
        final File tempFile = File.createTempFile(UUID.randomUUID().toString(), suffixName);
        multipartFile.transferTo(tempFile);
        return tempFile;
    }

    /**
     * 批量删除文件
     * @details 孤城落寞 2019-02-14 10:08
     * @param files
     * @return void
     */
    public void deleteFile(File... files){
        if (files.length > 1){
            for (File file : files){
                if (file.exists()){
                    file.delete();
                }
            }
        } else if (files.length == 1){
          files[0].delete();
        }
    }

    /**
     * 判断文件是否存在,如果不存在就创建该文件
     * @details 孤城落寞 2019-02-14 20:33
     * @param path
     * @return boolean
     */
    public File judgeFileExists(String path) throws IOException {

        ObjectOperationUtils objectOperationUtils = new ObjectOperationUtils();

        if (!objectOperationUtils.isEmpty(path)){

            File file = new File(path);
            //            log.info("父目录："+file.getParent());

            File dirFile = new File(file.getParent());

            if(!dirFile.exists() && !dirFile.isDirectory()){
                dirFile.mkdirs();
                //                log.info("创建多级目录完成 。。。。。");
            }

            if(!file.exists()){
                file.createNewFile();
                log.info("创建多级目录文件完成。。。");
                return file;
            }
        }
        return null;
    }
}
