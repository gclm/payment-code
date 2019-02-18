package club.gclmit.payment.qr.service.impl;

import club.gclmit.payment.qr.dao.UserDao;
import club.gclmit.payment.qr.model.entity.User;
import club.gclmit.payment.qr.service.UserService;
import club.gclmit.payment.qr.utils.IdWorker;
import club.gclmit.payment.qr.utils.ObjectOperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.payment.qr.service.impl
 * @author: gclm
 * @date: 2019-02-13 14:32
 * @description:
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    ObjectOperationUtils objectOperationUtils;

    @Override
    public User insertUser(User user) {
        User findUser = userDao.findUserByEmailOrUsername(user.getEmail(), user.getUsername());
        if(objectOperationUtils.isEmpty(findUser)){
            user.setId(idWorker.nextId()+"");
            return userDao.save(user);
        }
        return null;
    }

    @Override
    public boolean loginUserByUsernameAndPassword(User user) {
        User findUser = userDao.findUserByUsername(user.getUsername());
        if (findUser !=null && user.getPassword().equals(findUser.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public User selectUserById(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> selectUsers() {
        return userDao.findAll();
    }

    @Override
    public Page<User> selectUsersByUsernameLike(String username, Pageable pageable) {
        String usernameLike = new StringBuilder().append("%").append(username).append("%").toString();
        return userDao.findUsersByUsernameLike(usernameLike,pageable);
    }

    @Override
    public User updateUserById(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        userDao.deleteById(id);
    }
}
