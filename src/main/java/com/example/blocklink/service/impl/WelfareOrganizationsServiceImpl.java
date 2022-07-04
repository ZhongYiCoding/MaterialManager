package com.example.blocklink.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blocklink.entity.*;
import com.example.blocklink.mapper.ContributionApproveMapper;
import com.example.blocklink.mapper.RequestApproveMapper;
import com.example.blocklink.mapper.WelfareOrganizationsMapper;
import com.example.blocklink.service.IWelfareOrganizationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2022-06-28
 */
@Slf4j
@Service
@Transactional
public class WelfareOrganizationsServiceImpl extends ServiceImpl<WelfareOrganizationsMapper, WelfareOrganizations> implements IWelfareOrganizationsService {
    @Autowired
    private WelfareOrganizationsMapper welfareOrganizationsMapper;
    @Autowired
    private RequestApproveMapper requestApproveMapper;
    @Autowired
    private ContributionApproveMapper contributionApproveMapper;

    @Override
    public WelfareOrganizations getWelfareOrganizationsImfor(int account) {
        WelfareOrganizations welfareOrganizations = new WelfareOrganizations();
        QueryWrapper<WelfareOrganizations> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        welfareOrganizations = welfareOrganizationsMapper.selectOne(queryWrapper);
        return welfareOrganizations;
    }

    @Override
    public JSONArray getAllApplyContributionApprove(int woaccount) {
        QueryWrapper<ContributionAapprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("woaccount",woaccount);
        List<ContributionAapprove> list =  contributionApproveMapper.selectList(queryWrapper);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for(int i=0;i<list.size();i++){
            String json = JSON.toJSONString(list.get(i));
            jsonObject =  JSONObject.parseObject(json);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public JSONArray getAlearyDoneApplyContributionApprove(int woaccount) {
        QueryWrapper<ContributionAapprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("woaccount",woaccount);
        queryWrapper.or()
                .eq("state",2)
                .eq("state",3);
        List<ContributionAapprove> list =  contributionApproveMapper.selectList(queryWrapper);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for(int i=0;i<list.size();i++){
            String json = JSON.toJSONString(list.get(i));
            jsonObject =  JSONObject.parseObject(json);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public JSONArray getNoneDoneApplyContributionApprove(int woaccount) {
        QueryWrapper<ContributionAapprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("woaccount",woaccount);
        queryWrapper.eq("state",1);
        List<ContributionAapprove> list =  contributionApproveMapper.selectList(queryWrapper);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for(int i=0;i<list.size();i++){
            String json = JSON.toJSONString(list.get(i));
            jsonObject =  JSONObject.parseObject(json);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public JSONArray getAllApplyRequestApprove(int woaccount) {
        QueryWrapper<RequestApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("woaccount",woaccount);
        List<RequestApprove> list =  requestApproveMapper.selectList(queryWrapper);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for(int i=0;i<list.size();i++){
            String json = JSON.toJSONString(list.get(i));
            jsonObject =  JSONObject.parseObject(json);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public JSONArray getAlearyDoneApplyRequestApprove(int woaccount) {
        QueryWrapper<RequestApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("woaccount",woaccount);
        queryWrapper.or()
                .eq("state",2)
                .eq("state",3);
        List<RequestApprove> list =  requestApproveMapper.selectList(queryWrapper);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for(int i=0;i<list.size();i++){
            String json = JSON.toJSONString(list.get(i));
            jsonObject =  JSONObject.parseObject(json);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public JSONArray getNoneDoneApplyRequestApprove(int woaccount) {
        QueryWrapper<RequestApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("woaccount",woaccount);
        queryWrapper.eq("state",1);
        List<RequestApprove> list =  requestApproveMapper.selectList(queryWrapper);

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for(int i=0;i<list.size();i++){
            String json = JSON.toJSONString(list.get(i));
            jsonObject =  JSONObject.parseObject(json);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


}
