package club.gclmit.payment.qr.controller;

import club.gclmit.payment.qr.model.entity.User;
import club.gclmit.payment.qr.model.enums.StatusCode;
import club.gclmit.payment.qr.model.result.Result;
import club.gclmit.payment.qr.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.paymentcode.controller
 * @author: gclm
 * @date: 2019-02-12 10:02
 * @description: 用户controller
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result login(User user){
        if(userService.loginUserByUsernameAndPassword(user)){
            return new Result();
        }
        return new Result(false, StatusCode.ERROR,"登录失败，账号或者密码错误！！！");
    }

}
