package com.example.blocklink.service;

import com.example.blocklink.entity.Commonuser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-06-28
 */
public interface ICommonuserService extends IService<Commonuser> {
    Commonuser getCommonuserImfor(int account);

    boolean applyVoucher(int account,String context);

    boolean applyContribution(int account,int money,int dedical,int food);

    boolean applyRequest(int account,int money,int dedical,int food);

}
