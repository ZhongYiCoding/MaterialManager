package com.example.blocklink.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blocklink.entity.Commonuser;
import com.example.blocklink.entity.ContributionAapprove;
import com.example.blocklink.entity.RequestApprove;
import com.example.blocklink.entity.VoucherApprove;
import com.example.blocklink.mapper.CommonuserMapper;
import com.example.blocklink.mapper.ContributionApproveMapper;
import com.example.blocklink.mapper.RequestApproveMapper;
import com.example.blocklink.mapper.VoucherApproveMapper;
import com.example.blocklink.service.ICommonuserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

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
public class CommonuserServiceImpl extends ServiceImpl<CommonuserMapper, Commonuser> implements ICommonuserService {

    @Autowired
    private CommonuserMapper commonuserMapper;
    @Autowired
    private VoucherApproveMapper voucherApproveMapper;
    @Autowired
    private ContributionApproveMapper contributionApproveMapper;
    @Autowired
    private RequestApproveMapper requestApproveMapper;

    @Override
    public Commonuser getCommonuserImfor(int account) {
        Commonuser commonuser = new Commonuser();
        QueryWrapper<Commonuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        commonuser = commonuserMapper.selectOne(queryWrapper);
        return commonuser;
    }

    @Override
    public boolean applyVoucher(int account,String context) {
        VoucherApprove voucher=getVoucher(account);
        Timestamp now = new Timestamp((new Date()).getTime());
        Timestamp ttl=voucher.getDatetime();
        if (ttl.toLocaleString()==""||now.before(ttl)){
            return false;
        }

        VoucherApprove voucherApprove = new VoucherApprove();
        voucherApprove.setAccount(account);
        voucherApprove.setState(1);
        voucherApprove.setContext(context);
        voucherApprove.setCaaccount(100);
        int r = voucherApproveMapper.insert(voucherApprove);
        if(r == 1)return true;
        else return false;
    }

    @Override
    public boolean applyContribution(int account, int money, int dedical, int food) {
        ContributionAapprove contributionAapprove = new ContributionAapprove();
        contributionAapprove.setState(1);
        contributionAapprove.setAccount(account);
        contributionAapprove.setMoney(money);
        contributionAapprove.setMedical(dedical);
        contributionAapprove.setFood(food);
        contributionAapprove.setWoaccount(110);
        int r = contributionApproveMapper.insert(contributionAapprove);
        if(r==1)return true;
        else return false;
    }

    @Override
    public boolean applyRequest(int account, int money, int dedical, int food) {
        RequestApprove requestApprove = new RequestApprove();
        requestApprove.setState(1);
        requestApprove.setAccount(account);
        requestApprove.setMoney(money);
        requestApprove.setMedical(dedical);
        requestApprove.setFood(food);
        requestApprove.setWoaccount(110);
        int r = requestApproveMapper.insert(requestApprove);
        if(r==1)return true;
        else return false;
    }

    public VoucherApprove getVoucher(Integer account) {
        QueryWrapper<VoucherApprove> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        VoucherApprove voucherApprove = voucherApproveMapper.selectOne(queryWrapper);

        return voucherApprove;
    }
}
