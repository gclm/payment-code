package club.gclmit.payment.qr.controller;

import club.gclmit.payment.qr.model.entity.User;
import club.gclmit.payment.qr.model.enums.StatusCode;
import club.gclmit.payment.qr.model.result.Result;
import club.gclmit.payment.qr.service.UserService;
import club.gclmit.payment.qr.utils.ObjectOperationUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private ObjectOperationUtils objectOperationUtils;

    @PostMapping("/login")
    public Result login(User user){
        if(userService.loginUserByUsernameAndPassword(user)){
            return new Result();
        }
        return new Result(false, StatusCode.ERROR,"登录失败，账号或者密码错误！！！");
    }

    @PostMapping
    public Result addUser(@RequestBody JSONObject jsonObject){

        User saveUser = userService.insertUser(new User());
        if(!objectOperationUtils.isEmpty(saveUser)){
            return new Result(true,StatusCode.OK,"账号登录成功",saveUser);
        }
        return new Result(false,StatusCode.ERROR,"账号注册失败!!!,该账号账号或邮箱已被注册！！！");
    }

    @GetMapping
    public Result selectUsers(){
        List<User> users = userService.selectUsers();
        if (objectOperationUtils.isEmpty(users)){
            return new Result(false,StatusCode.ERROR,"空空如也");
        }
        return new Result(true,StatusCode.OK,"所有用户查询成功！！！",userService.selectUsers());
    }

    @GetMapping("/{id}")
    public  Result selectUserById(@PathVariable  String id){
        User user = userService.selectUserById(id);
        if(objectOperationUtils.isEmpty(user)){
            return new Result(false,StatusCode.ERROR,"不存在该用户");
        }
        return new Result(true,StatusCode.OK,"用户查询成功！！！", user);
    }

    @GetMapping("/{username}")
    public Result selectUserByUsernameLike(@PathVariable String username){
//        userService.selectUsersByUsernameLike(username,);
        return new Result();
    }
}
