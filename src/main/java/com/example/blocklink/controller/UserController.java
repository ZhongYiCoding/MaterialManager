package com.example.blocklink.controller;


import com.example.blocklink.entity.User;
import com.example.blocklink.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2022-06-28
 */
@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/login")
    public boolean login(@Param("account")Integer account, @Param("password")Integer password, HttpSession httpSession){
        User user = userService.login(account,password);
        if(user!=null){
            httpSession.setAttribute("user",user);
            return true;
        }else return false;
    }

    @GetMapping("/logout")
    public void logout(HttpSession httpSession){
        httpSession.invalidate();
    }

    @GetMapping("/changePassword")
    public boolean changePassword(@Param("account")Integer account, @Param("password")Integer password, @Param("newpassword")Integer newpassword){
        return userService.changePassword(account,password,newpassword);
    }

    @GetMapping("/register")
    public boolean register(@Param("account")Integer account, @Param("password")Integer password,@Param("role")Integer role){
        return userService.register(account, password, role);
    }


}
