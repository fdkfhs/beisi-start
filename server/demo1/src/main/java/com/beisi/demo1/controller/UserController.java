package com.beisi.demo1.controller;

import com.beisi.demo1.dto.UserLoginDTO;
import com.beisi.demo1.entity.User;
import com.beisi.demo1.result.Result;
import com.beisi.demo1.service.UserService;
import com.beisi.demo1.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/api/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("用户登录：{}",userLoginDTO.getUsername());

        User user = userService.login(userLoginDTO);
        if(user==null){
            return Result.error("Invalid username or password");
        }
        String token= UUID.randomUUID().toString().replace("-", "");
        UserLoginVO userLoginVO=new UserLoginVO();
        userLoginVO.setToken(token);
        return Result.success(userLoginVO);
    }
}
