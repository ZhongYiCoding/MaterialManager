package com.example.blocklink.controller;


import com.alibaba.fastjson.JSONArray;
import com.example.blocklink.entity.CertificateAuthority;
import com.example.blocklink.entity.Commonuser;
import com.example.blocklink.entity.User;
import com.example.blocklink.service.impl.CertificateAuthorityServiceImpl;
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
@RequestMapping("/CertiAuth")
@CrossOrigin
public class CertificateAuthorityController {

    @Autowired
    private CertificateAuthorityServiceImpl certificateAuthorityService;

    @GetMapping("/getImfor")//
    public ResponseEntity<CertificateAuthority> getImfor(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return ResponseEntity.ok(certificateAuthorityService.getCertificateAuthorityImfor(100));
    }

    @GetMapping("/getAllApply")
    public JSONArray getAllApply(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return certificateAuthorityService.getAllApply(100);
    }

    @GetMapping("/getAlearyDoneApply")
    public JSONArray getAlearyDoneApply(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return certificateAuthorityService.getAlearyDoneApply(100);
    }

    @GetMapping("/getNoneDoneApply")
    public JSONArray getNoneDoneApply(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return certificateAuthorityService.getNoneDoneApply(100);
    }

    @GetMapping("/agreeApply")
    public boolean agreeApply(@Param("account")int account){
        certificateAuthorityService.sendCertificate(account);
        return certificateAuthorityService.agreeApply(account);
    }

    @GetMapping("/refuseApply")
    public boolean refuseApply(@Param("account")int account){
        return certificateAuthorityService.refuseApply(account);
    }

}
