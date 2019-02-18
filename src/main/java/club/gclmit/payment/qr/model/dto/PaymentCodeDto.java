package club.gclmit.payment.qr.model.dto;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.model.dto
 * @author: gclm
 * @date: 2019-02-13 21:35
 * @description:
 */
public class PaymentCodeDto {

    private String id;

    private String title;

    private String value;

    public PaymentCodeDto(String id, String title, String value) {
        this.id = id;
        this.title = title;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
