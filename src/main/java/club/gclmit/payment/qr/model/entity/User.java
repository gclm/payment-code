package club.gclmit.payment.qr.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.entity
 * @author: gclm
 * @date: 2019-02-13 09:54
 * @description:
 */
@Data
@Entity
@Table(name = "pay_user")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    /**
     * 用户 id
     */
    @Id
    @Column(nullable = false,length = 50)
    private String id;

    /**
     * 账号
     */
    @NotEmpty(message = "账号不能为空")
    @Size(min = 4,max = 10,message = "账号由4~10位数字或者字母或汉字组成")
    @Column(nullable = false,length = 20)
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Size(min = 6,max = 16,message = "密码由6~16位数字或者字母或特殊字符组成")
    @Column(nullable = false,length = 20)
    private String password;

    /**
     * 邮箱
     */
    @NotEmpty(message = "密码不能为空")
    @Email(message = "邮箱格式不对")
    @Column(nullable = false,length = 50)
    private String email;

    /**
     * 昵称
     */
    @Size(min = 2,max = 10,message = "昵称有2~10位数字或者字母或汉字组成")
    @Column(length = 50)
    private String nickname;
}
