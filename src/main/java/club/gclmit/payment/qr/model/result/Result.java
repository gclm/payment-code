package club.gclmit.payment.qr.model.result;

import lombok.*;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.share
 * @author: gclm
 * @date: 2018-12-19 16:53
 * @description: 返回数据封装
 */
@Getter
@Setter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Result {

     /**
      *  是否响应成功
      */
     private boolean flag;

     /**
      *  响应状态码
      */
     private Integer code;

     /**
      *  响应提示
      */
     private String message;

     /**
      *  响应数据
      */
     private Object data;

     public Result(boolean flag, Integer code, String message) {
          this.flag = flag;
          this.code = code;
          this.message = message;
     }
}
