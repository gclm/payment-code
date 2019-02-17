package club.gclmit.payment.qr.controller;

import club.gclmit.payment.qr.model.dto.GenerateCodeDto;
import club.gclmit.payment.qr.model.dto.PaymentCodeDto;
import club.gclmit.payment.qr.model.enums.StatusCode;
import club.gclmit.payment.qr.model.result.Result;
import club.gclmit.payment.qr.service.PaymentCodeService;
import club.gclmit.payment.qr.utils.FileConversionUtil;
import club.gclmit.payment.qr.utils.ObjectIsNullUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.controller
 * @author: gclm
 * @date: 2019-02-13 14:58
 * @description:
 */
@Slf4j
@Controller
@RequestMapping("/qr")
public class PaymentCodeController {

    @Autowired
    private PaymentCodeService paymentCodeService;

    @Autowired
    private FileConversionUtil fileConversionUtil;

    @Autowired
    private ObjectIsNullUtils objectIsNullUtils;

    /**
     * 付款链接请求
     * @details 孤城落寞 2019-02-13 21:44
     * @param request  获取浏览器 User-Agent
     * @param id 用户ID
     * @return void
     */
    @GetMapping("/pay")
    public ModelAndView getPayment(HttpServletRequest request, @RequestParam(required = true) String id,
                                   Model model) throws IOException, WriterException {
        String userAgentToString = request.getHeader("User-Agent");

        log.info("user_agent: \n"+userAgentToString);

        String[] result = paymentCodeService.scanPaymentCode(userAgentToString, id);

        log.info("result null :"+result[0]);
        if (!objectIsNullUtils.isEmpty(result[0])){
            model.addAttribute("code",new StringBuilder().append("data:image/png;base64,").append(result[0]).toString());
            model.addAttribute("username",result[1]);
            return new ModelAndView("allpay","paymentCode",model);
        }
        model.addAttribute("message","二维码调用失败，不存在该聚合付款码！！！！！");
        return new ModelAndView("message","paymentCode",model);
    }

    @GetMapping
    public String goHome(){
        return "index";
    }

    /**
     * 解析二维码
     * @details 孤城落寞 2019-02-13 21:45
     * @param multipartFile 上传的文件
     * @param id 用户ID
     * @return club.gclmit.payment.qr.model.result.Result
     */
    @ResponseBody
    @PostMapping("/parse")
    public Result parsePaymentCode(@RequestParam(value = "file" ,required = true) MultipartFile multipartFile,
                             @RequestParam(required = true) String id) throws IOException, IllegalAccessException, NoSuchFieldException, NotFoundException {

        log.debug("file: "+multipartFile.getSize()+" id: "+id);

        if(multipartFile.isEmpty()){
            return new Result(false, StatusCode.UPLOAD_FILE_ERROR,"二维码解析失败，上传的文件为空");
        }

        File file = fileConversionUtil.multipartFileToFile(multipartFile);

        String[] results = paymentCodeService.parsePaymentCode(file, id);

        if(!StringUtils.isEmpty(results)){
            fileConversionUtil.deleteFile(file);
            return new Result(true,StatusCode.PARSE_QR_OK,"二维码解析成功",new PaymentCodeDto(results[0],results[1]));

        }
        return new Result(false, StatusCode.PARSE_QR_ERROR,"服务器繁忙，请稍后再试");
    }

    @ResponseBody
    @PostMapping
    public Result generatePaymentCode(@RequestBody JSONObject jsonParms, HttpServletRequest request) throws IOException, WriterException {

        String hostUrl = new StringBuilder().append("http://").append(request.getHeader("Host"))
                .append(request.getServletPath()).toString();

        System.out.println(jsonParms.toJSONString());

        if(!StringUtils.isEmpty(jsonParms)){
            String aliPay = jsonParms.getString("aliPay").trim();
            String weixinPay = jsonParms.getString("weixinPay").trim();
            String qqPay = jsonParms.getString("qqPay").trim();
            String username = jsonParms.getString("username").trim();
            if(aliPay != null && weixinPay !=null && qqPay != null){
                log.info("\n访问路径："+hostUrl+"\naliPay:"+aliPay+"\nweixinPay:"+weixinPay+"\nqqPay:"+qqPay);
                String paymentCode = paymentCodeService.generatePaymentCode(hostUrl, aliPay, qqPay, weixinPay,username);

                return new Result(true,StatusCode.OK,"二维码生成成功",new GenerateCodeDto(username,new StringBuilder().append("data:image/png;base64,").append(paymentCode).toString()));
            }
            return  new Result(false,StatusCode.ERROR,"二维码生成失败,\n当前服务器繁忙，请稍后再试");
        }
        return  new Result(false,StatusCode.ERROR,"二维码生成失败,\n当前服务器繁忙，请稍后再试");
    }
}
