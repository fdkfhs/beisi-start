package com.beisi.demo1.service.impl;

import com.beisi.demo1.dto.UserLoginDTO;
import com.beisi.demo1.entity.User;
import com.beisi.demo1.mapper.UserMapper;
import com.beisi.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(UserLoginDTO userLoginDTO){
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        User user = userMapper.getByUsername(username);

        if (user == null) {
            //账号不存在
            return null;
        }

        //对前端传过来的明文密码进行MD5加密处理
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            //密码错误
            return null;
        }

        return user;
    }
}
