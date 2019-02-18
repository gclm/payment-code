package club.gclmit.payment.qr.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.entity
 * @author: gclm
 * @date: 2019-02-13 10:07
 * @description:
 */
@Data
@Entity
@Table(name = "pay_code")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PaymentCode {

    /**
     * 主键
     */
    @Id
    @Column(nullable = false,length = 50)
    private String id;

    /**
     * 二维码上名字
     */
    @Column(length = 100)
    private String username;

    /**
     * QQ 付款码
     */
    @Column(length = 200,name = "qq_payment_code")
    private String qqPaymentCode;

    /**
     * 微信付款码
     */
    @Column(length = 50,name = "weixin_payment_code")
    private String weixinPaymentCode;

    /**
     * 支付宝付款码
     */
    @Column(length = 50,name = "alipay_payment_code")
    private String alipayPaymentCode;

    /**
     * 聚合码
     */
    @Column(length = 255,name = "payment_code")
    private String paymentCode;
}
