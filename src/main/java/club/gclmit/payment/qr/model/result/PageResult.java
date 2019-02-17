package club.gclmit.payment.qr.model.result;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.share.entity
 * @author: gclm
 * @date: 2018-12-19 16:55
 * @description: 分页参数返回对象实体类
 */
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class PageResult<T> {

    private long total;

    private List<T> rows;

}
