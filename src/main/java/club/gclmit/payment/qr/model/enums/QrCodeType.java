package club.gclmit.payment.qr.model.enums;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.model.enums
 * @author: gclm
 * @date: 2019-02-14 20:21
 * @description: 二维码 类型
 */
public enum QrCodeType {

    /**
     * 网络二维码
     */
    NET_QR_CODE("net"),

    /**
     * 本地二维码
     */
    LOCAL_QR_CODE("local");

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
    private QrCodeType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
