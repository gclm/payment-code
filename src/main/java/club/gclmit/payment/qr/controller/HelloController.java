package club.gclmit.payment.qr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.controller
 * @author: gclm
 * @date: 2019-02-13 19:40
 * @description:
 */
@RestController
public class HelloController  {


    @GetMapping("/hello")
    public String hello(){
        return "hello payment code";
    }
}
