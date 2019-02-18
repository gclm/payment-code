package club.gclmit.payment.qr.controller.exception;

import club.gclmit.payment.qr.model.enums.StatusCode;
import club.gclmit.payment.qr.model.result.Result;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.controller
 * @author: gclm
 * @date: 2019-02-18 14:22
 * @description:
 */
@Slf4j
@RestController
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    public Result defaultExceptionHanler(Exception e) {
        String message = null;
        String data = null;

        if(e instanceof IllegalAccessException){
            message = "输入非法异常，请稍后再试";
            data = e.getLocalizedMessage();
        }

        if(e instanceof NoSuchFieldException){
            message = "当前系统出现异常，请及时联系管理员，解决问题！！！";
            data = e.getLocalizedMessage();
        }

        if(e instanceof InvocationTargetException){
            message = "当前系统出现异常，请及时联系管理员，解决问题！！！";
            data = e.getLocalizedMessage();
        }

        if(e instanceof IOException){
            message = "不存在该文件";
            data = e.getLocalizedMessage();
        }

        if(e instanceof NotFoundException){
            message = "该图片不是二维码图片";
            data = e.getLocalizedMessage();
        }

        if(e instanceof WriterException){
            message = "二维码图片合成失败，请稍后再试";
            data = e.getLocalizedMessage();
        }

        return new Result(false, StatusCode.EXCEPTION_CODE,message,data);
    }

}
