package com.chen.blog.service;

import com.chen.blog.dao.pojo.SysUser;

public interface SysUserService {

    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);
}
