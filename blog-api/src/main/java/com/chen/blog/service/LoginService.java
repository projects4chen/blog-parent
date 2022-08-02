package com.chen.blog.service;

import com.chen.blog.vo.Result;
import com.chen.blog.vo.params.LoginParam;

public interface LoginService {
    /**
     * 登录功能
     * @param loginParam
     */
    Result login(LoginParam loginParam);
}
