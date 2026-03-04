package com.beisi.demo1.service;

import com.beisi.demo1.dto.UserLoginDTO;
import com.beisi.demo1.entity.User;

public interface UserService {
    User login(UserLoginDTO userLoginDTO);
}
