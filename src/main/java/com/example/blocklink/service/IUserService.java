package com.example.blocklink.service;

import com.example.blocklink.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-06-28
 */
public interface IUserService extends IService<User> {
    //
    User login(int acount,int password);
    //
    boolean changePassword(int acount,int password,int newpassword);
    //
    boolean register(int acount,int password,int role);

}
