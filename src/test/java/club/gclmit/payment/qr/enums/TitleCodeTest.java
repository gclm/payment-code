package club.gclmit.payment.qr.enums;

import club.gclmit.payment.qr.model.enums.TitileCode;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.enums
 * @author: gclm
 * @date: 2019-02-14 21:07
 * @description:
 */
public class TitleCodeTest {

    public static void main(String[] args) {
        System.out.println("直接枚举："+TitileCode.ALI_PAY_CODE);
        System.out.println("直接枚举 Desc："+TitileCode.ALI_PAY_CODE.getDesc());
        System.out.println("String: "+String.valueOf(TitileCode.ALI_PAY_CODE));
    }
}
