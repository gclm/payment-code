package club.gclmit.payment.qr.dao;

import club.gclmit.payment.qr.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.dao
 * @author: gclm
 * @date: 2019-02-13 12:15
 * @description:
 */
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {


    /**
     * 根据账号分页模糊查询用户
     * @details 孤城落寞 2019-02-13 14:54
     * @param username	账号
     * @param pageable	分页参数
     * @return
     */
    Page<User> findUsersByUsernameLike(String username, Pageable pageable);


    /**
     * 根据账号查找用户
     */
    User findUserByUsername(String username);

    /**
     * 根据账户或邮箱查找用户
     * @details 孤城落寞 2019-02-17 17:03
     * @param email
     * @param username
     * @return
     */
    User findUserByEmailOrUsername(String email,String username);

    /**
     * 根绝 Id 查询用户
     * @details 孤城落寞 2019-02-17 17:10
     * @param id
     * @return
     */
    User findUserById(String id);
}
