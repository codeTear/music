package com.hyh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.hyh.dao.UserDao;

import com.hyh.domain.User;
import com.hyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.baomidou.mybatisplus.extension.toolkit.Db.update;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    //插入用户的信息
    @Override
    public boolean save(User user) {
        int change = userDao.insert(user);
        return change > 0;
    }

    @Override
    public boolean selectUsernamePwd(User user) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        QueryWrapper<User> username1 = qw.eq("username", user.getUsername());
        User user1 = userDao.selectOne(username1);
        if(user1==null){
            return false;
        }
        return user1.getPassword().equals(user.getPassword());
    }

    //根据用户名查找用户信息
    @Override
    public boolean selectByName(String username) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        QueryWrapper<User> username1 = qw.eq("username", username);
        User user = userDao.selectOne(username1);
        return user !=null;
    }

    //根据用户名修改密码
    public boolean updatePwd(User user){
        int count = userDao.updateById(user);
        System.out.println(count);
        return count > 0;
    }

    //判断邮箱是否重复
    @Override
    public User selectEmail(String email) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        QueryWrapper<User> email_query = qw.eq("email", email);
        User user = userDao.selectOne(email_query);
        return user ;
    }

    //根据id查找用户
    @Override
    public User selectById(Integer id) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        QueryWrapper<User> id_query = qw.eq("id", id);
        return userDao.selectOne(id_query);
    }

    //token查询信息
    @Override
    public User selectByNameToken(String username) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        QueryWrapper<User> username1 = qw.eq("username", username);
        return userDao.selectOne(username1);
    }

    //根据用户名修改用户信息
    @Override
    public User updateInfoByName(User user) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username", user.getUsername());
        User user1 =  userDao.selectOne(qw);
        if (user1 != null) {
            user1.setUserImg(user.getUserImg());
            userDao.update(user1, qw);
        }
        return user1;
    }

    //根据id修改用户信息
    @Override
    public Boolean updateInfo(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", user.getId()).setEntity(new User());  // 根据 ID 进行更新
        // 设置需要更新的字段和值
        updateWrapper.set("username", user.getUsername());
        updateWrapper.set("email", user.getEmail());
        updateWrapper.set("date", user.getDate());
        updateWrapper.set("sex", user.getSex());
        updateWrapper.set("description", user.getDescription());
        return update(updateWrapper);
    }
}
