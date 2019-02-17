package club.gclmit.payment.qr.config;

import lombok.*;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.config
 * @author: gclm
 * @date: 2019-02-13 15:53
 * @description:
 */
@Getter
@Setter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QrConfig {

    /**
     * 编码格式
     */
    private String charaset = "UTF-8";

    /**
     * 图片格式
     */
    private String fomatName = "JPG";

    /**
     * 二维码大小
     */
    private Integer size = 300;

    /**
     * logo 宽
     */
    private Integer logoWidth = 60;

    /**
     * logo 高
     */
    private Integer loginHeight = 60;

    public QrConfig(String fomatName, Integer size) {
        this.fomatName = fomatName;
        this.size = size;
    }

    public QrConfig(Integer size, Integer logoWidth, Integer loginHeight) {
        this.size = size;
        this.logoWidth = logoWidth;
        this.loginHeight = loginHeight;
    }
}
