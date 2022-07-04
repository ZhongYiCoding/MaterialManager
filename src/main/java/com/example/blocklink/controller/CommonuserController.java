package com.example.blocklink.controller;


import com.example.blocklink.entity.Commonuser;
import com.example.blocklink.entity.User;
import com.example.blocklink.service.impl.CommonuserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/commonuser")
@CrossOrigin
public class CommonuserController {

    @Autowired
    private CommonuserServiceImpl commonuserService;

    @GetMapping("/getImfor")//
    public Commonuser getImfor(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return commonuserService.getCommonuserImfor(101);
    }

    @GetMapping("/applyVoucher")
    public boolean applyVoucher(@Param("context")String context, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return commonuserService.applyVoucher(101,context);
    }

    @GetMapping("/applyContribution")
    public boolean applyContribution(@Param("money")Integer money,@Param("medical")Integer medical,@Param("food")Integer food, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return commonuserService.applyContribution(101,money,medical,food);
    }

    @GetMapping("/applyRequest")
    public boolean applyRequest(@Param("money")Integer money,@Param("medical")Integer medical,@Param("food")Integer food, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return commonuserService.applyRequest(101,money,medical,food);
    }

}
