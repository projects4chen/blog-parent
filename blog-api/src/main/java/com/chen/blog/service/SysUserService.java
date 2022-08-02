package com.chen.blog.service;

import com.chen.blog.dao.pojo.SysUser;
import com.chen.blog.vo.Result;
import com.chen.blog.vo.UserVo;

public interface SysUserService {

    UserVo findUserVoById(Long id);

    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);

    /**
     * 根据账户查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);

    /**
     * 保存账户
     * @param sysUser
     */
    void save(SysUser sysUser);
}
