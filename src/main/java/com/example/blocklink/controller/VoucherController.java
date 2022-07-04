package com.example.blocklink.controller;


import com.example.blocklink.service.impl.VoucherServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

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
@CrossOrigin
public class VoucherController {

    @Autowired
    private VoucherServiceImpl voucherService;
}
