package club.gclmit.payment.qr.config;

import club.gclmit.payment.qr.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.config
 * @author: gclm
 * @date: 2019-02-13 09:58
 * @description:
 */
@Component
public class IdConfig {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1, 1);
    }
}
