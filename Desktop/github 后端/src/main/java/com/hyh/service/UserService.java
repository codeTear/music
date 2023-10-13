package com.hyh.service;

import com.hyh.domain.PageResult;
import com.hyh.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    //保存用户的信息
    public boolean save(User user);
    //校验用户名和密码是否一样
    public boolean selectUsernamePwd(User user);
    //根据用户名查询信息
    public boolean selectByName(String username);
    //更新字段名
    public boolean updatePwd(User user);
    //根据邮箱查找信息
    public User selectEmail(String email);

    //根据id查询用户信息
    public User selectById(Integer id);

    //根据用户名查信息
    public User selectByNameToken(String username);

    //根据用户名修改用户信息
    public User updateInfoByName(User user);

    //根据id修改用户信息
    public Boolean updateInfo(User user);

    //分页查询用户信息

}
