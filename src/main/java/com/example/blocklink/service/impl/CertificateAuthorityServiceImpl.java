package com.example.blocklink.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blocklink.contractService.CertClient;
import com.example.blocklink.entity.*;
import com.example.blocklink.mapper.CertificateAuthorityMapper;
import com.example.blocklink.mapper.VoucherApproveMapper;
import com.example.blocklink.mapper.VoucherMapper;
import com.example.blocklink.service.ICertificateAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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
public class CertificateAuthorityServiceImpl extends ServiceImpl<CertificateAuthorityMapper, CertificateAuthority> implements ICertificateAuthorityService {

    @Autowired
    private CertificateAuthorityMapper certificateAuthorityMapper;
    @Autowired
    private VoucherApproveMapper voucherApproveMapper;
    @Autowired
    private VoucherMapper voucherMapper;
    private CertClient certClient = new CertClient();

    @Override
    public CertificateAuthority getCertificateAuthorityImfor(Integer account) {
        CertificateAuthority certificateAuthority = new CertificateAuthority();
        QueryWrapper<CertificateAuthority> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        certificateAuthority = certificateAuthorityMapper.selectOne(queryWrapper);
        return certificateAuthority;
    }

    @Override
    public void sendCertificate(Integer _uid) {
        QueryWrapper<Voucher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_belong",_uid);
        Voucher voucher = voucherMapper.selectOne(queryWrapper);
        QueryWrapper<VoucherApprove> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("account",_uid);
        VoucherApprove voucherApprove = voucherApproveMapper.selectOne(queryWrapper1);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        certClient.sendCertificate(_uid.toString(),voucherApprove.getContext(),dateFormat.format(date));
    }

    @Override
    public boolean agreeApply(Integer account) {
        QueryWrapper<VoucherApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        VoucherApprove voucherApprove = voucherApproveMapper.selectOne(queryWrapper);
        voucherApprove.setState(2);
        int r = voucherApproveMapper.update(voucherApprove,queryWrapper);
        if(r==1)return true;
        else return false;
    }

    @Override
    public boolean refuseApply(Integer account) {
        QueryWrapper<VoucherApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        VoucherApprove voucherApprove = voucherApproveMapper.selectOne(queryWrapper);
        voucherApprove.setState(3);
        int r = voucherApproveMapper.update(voucherApprove,queryWrapper);
        if(r==1)return true;
        else return false;
    }

    @Override
    public JSONArray getAllApply(Integer caaccount) {
        QueryWrapper<VoucherApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("caaccount",caaccount);
        List<VoucherApprove> list =  voucherApproveMapper.selectList(queryWrapper);

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
    public JSONArray listAllDone(Integer caaccount) throws Exception {
        //从合约返回的数据拿出来
        List<String> _userID = new LinkedList<>();List<String> _userInfo=new LinkedList<>();List<BigInteger> _type =new LinkedList<>();
        Tuple3 tuple =new Tuple3(_userID,_userInfo,_type);
        tuple = certClient.listAllDone();
        List<String> userID=(List<String>) tuple.getValue1();List<String> userInfo=(List<String>) tuple.getValue2();List<BigInteger> type=(List<BigInteger>) tuple.getValue3();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for(int i=0;i<userID.size();i++){
            VoucherDoneList list=new VoucherDoneList();
            list.setAccount(Integer.valueOf(userID.get(i)));
            list.setContext(userInfo.get(i));
            list.setType(Integer.valueOf(String.valueOf(type.get(i))));
            String json = JSON.toJSONString(list);
            jsonObject =  JSONObject.parseObject(json);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }


    @Override
    public JSONArray getAlearyDoneApply(Integer caaccount) {
        QueryWrapper<VoucherApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("caaccount",caaccount);
        queryWrapper.or()
                .eq("state",2)
                .eq("state",3);
        List<VoucherApprove> list =  voucherApproveMapper.selectList(queryWrapper);

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
    public JSONArray getNoneDoneApply(Integer caaccount) {
        QueryWrapper<VoucherApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("caaccount",caaccount);
        queryWrapper.eq("state",1);
        List<VoucherApprove> list =  voucherApproveMapper.selectList(queryWrapper);

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
