package club.gclmit.payment.qr.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.model.dto
 * @author: gclm
 * @date: 2019-02-16 10:54
 * @description:
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class GenerateCodeDto {

    private String username;

    private String code;
}
