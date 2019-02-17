package club.gclmit.payment.qr.dao;

import club.gclmit.payment.qr.model.entity.PaymentCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.dao
 * @author: gclm
 * @date: 2019-02-13 12:18
 * @description:
 */
public interface PaymentCodeDao extends JpaRepository<PaymentCode,String>, JpaSpecificationExecutor<PaymentCode> {

    /**
     * 通过用户id 查询 PaymentQr
     * @details 孤城落寞 2019-02-14 11:10
     * @param userId 用户id
     * @return
     */
    PaymentCode findPaymentCodeByUserId(String userId);

    /**
     * 通过用户id 查询 PaymentQr
     * @details 孤城落寞 2019-02-14 11:10
     * @param id id
     * @return
     */
    PaymentCode findPaymentCodeById(String id);

    /**
     * 通过 QQ 微信 支付宝 获取 PaymentQr
     * @details 孤城落寞 2019-02-14 11:48
     * @param aliPay
     * @param qqPay
     * @param weixinPay
     * @return
     */
    @Query(value = "SELECT * FROM pay_code WHERE alipay_payment_code = ? AND  qq_payment_code = ? AND  weixin_payment_code = ?",nativeQuery = true)
    PaymentCode findPaymentCodeByCompoundCode(String aliPay,String qqPay,String weixinPay);

}
