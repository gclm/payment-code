package club.gclmit.payment.qr.service;

import club.gclmit.payment.qr.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.service
 * @author: gclm
 * @date: 2019-02-13 14:32
 * @description: 用户service
 */
public interface UserService {

    public User insertUser(User user);

    public boolean loginUserByUsernameAndPassword(User user);

    public User selectUserById(String id);

    public List<User> selectUsers();

    public Page<User> selectUsersByUsernameLike(String username, Pageable pageable);

    public User updateUserById(User user);

    public void deleteUserById(String id);
}
