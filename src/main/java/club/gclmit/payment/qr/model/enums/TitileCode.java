package club.gclmit.payment.qr.model.enums;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.entity
 * @author: gclm
 * @date: 2019-02-13 14:28
 * @description: 项目状态码
 */
public enum TitileCode {

    /**  效验码  **/

    /**
     * QQ/TIM 校验码 QQ
     */
    QQ_PAY_CODE("QQ"),

    /**
     * 微信校验码  MMWEBID
     */
    WEIXIN_PAY_CODE("MMWEBID"),

    /**
     * 支付宝校验码 AlipayClient
     */
    ALI_PAY_CODE("AlipayClient"),


    /**  验证码  **/


    /**
     * QQ/TIM 验证码 vac.qq.com
     */
    QQ_JUDGE_CODE("vac.qq.com"),

    /**
     * 微信验证码 wxp
     */
    WEIXIN_JUDGE_CODE("wxp"),

    /**
     * 支付宝验证码 qr.alipay.com
     */
    ALI_JUDGE_CODE("qr.alipay.com");


    /**
     * 描述
     */
    private String desc;

    /**
     * 私有 构造器 防止外部调用
     * @details 孤城落寞 2019-02-14 21:00
     * @param desc
     * @return
     */
    private TitileCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
