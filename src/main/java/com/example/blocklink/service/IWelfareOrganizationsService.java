package com.example.blocklink.service;

import com.alibaba.fastjson.JSONArray;
import com.example.blocklink.entity.Commonuser;
import com.example.blocklink.entity.WelfareOrganizations;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-06-28
 */
public interface IWelfareOrganizationsService extends IService<WelfareOrganizations> {
    WelfareOrganizations getWelfareOrganizationsImfor(int account);
    JSONArray getAllApplyContributionApprove(int woaccount);
    JSONArray getAlearyDoneApplyContributionApprove(int woaccount);
    JSONArray getNoneDoneApplyContributionApprove(int woaccount);
    JSONArray getAllApplyRequestApprove(int woaccount);
    JSONArray getAlearyDoneApplyRequestApprove(int woaccount);
    JSONArray getNoneDoneApplyRequestApprove(int woaccount);
}
