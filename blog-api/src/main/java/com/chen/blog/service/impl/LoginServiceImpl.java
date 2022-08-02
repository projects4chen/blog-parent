package com.chen.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.chen.blog.dao.pojo.SysUser;
import com.chen.blog.service.LoginService;
import com.chen.blog.service.SysUserService;
import com.chen.blog.utils.JWTUtils;
import com.chen.blog.vo.ErrorCode;
import com.chen.blog.vo.Result;
import com.chen.blog.vo.params.LoginParam;
import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private static final String salt = "mszlu!@#";

    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1. 检查参数是否合法
         * 2. 根据用户名和密码去user表中查询是否存在
         * 3. 如果不存在 登录失败
         * 4. 如果存在，使用jwt生成token，返回给前端
         * 5. token放入redis中，redis token：user信息 设置过期时间
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        // 参数为空
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        password = DigestUtils.md5Hex(password + salt);
        // 用户不存在
        SysUser sysUser = sysUserService.findUser(account, password);
        if (sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        // 生成token
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        if(StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap == null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }
}
