package com.example.blocklink.controller;


import com.alibaba.fastjson.JSONArray;
import com.example.blocklink.entity.Commonuser;
import com.example.blocklink.entity.User;
import com.example.blocklink.entity.WelfareOrganizations;
import com.example.blocklink.service.impl.CommonuserServiceImpl;
import com.example.blocklink.service.impl.WelfareOrganizationsServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/welforg")
@CrossOrigin
public class WelfareOrganizationsController {

    @Autowired
    private WelfareOrganizationsServiceImpl welfareOrganizationsService;

    @GetMapping("/getImfor")//
    public ResponseEntity<WelfareOrganizations> getImfor(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return ResponseEntity.ok(welfareOrganizationsService.getWelfareOrganizationsImfor(110));
    }

    @GetMapping("/getAllApplyContributionApprove")
    public JSONArray getAllApplyContributionApprove(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return welfareOrganizationsService.getAllApplyContributionApprove(110);
    }

    @GetMapping("/getAlearyDoneApplyContributionApprove")
    public JSONArray getAlearyDoneApplyContributionApprove(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return welfareOrganizationsService.getAlearyDoneApplyContributionApprove(110);
    }

    @GetMapping("/getNoneDoneApplyContributionApprove")
    public JSONArray getNoneDoneApplyContributionApprove(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return welfareOrganizationsService.getNoneDoneApplyContributionApprove(110);
    }


    @GetMapping("/getAllApplyRequestApprove")
    public JSONArray getAllApplyRequestApprove(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return welfareOrganizationsService.getAllApplyRequestApprove(110);
    }

    @GetMapping("/getAlearyDoneApplyRequestApprove")
    public JSONArray getAlearyDoneApplyRequestApprove(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return welfareOrganizationsService.getAlearyDoneApplyRequestApprove(110);
    }

    @GetMapping("/getNoneDoneApplyRequestApprove")
    public JSONArray getNoneDoneApplyRequestApprove(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return welfareOrganizationsService.getNoneDoneApplyRequestApprove(110);
    }

}
