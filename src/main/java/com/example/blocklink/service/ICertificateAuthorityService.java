package com.example.blocklink.service;

import com.alibaba.fastjson.JSONArray;
import com.example.blocklink.entity.CertificateAuthority;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blocklink.entity.WelfareOrganizations;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-06-28
 */
public interface ICertificateAuthorityService extends IService<CertificateAuthority> {
    CertificateAuthority getCertificateAuthorityImfor(Integer account);

    void sendCertificate(Integer _uid);
    boolean agreeApply(Integer account);
    boolean refuseApply(Integer account);
    JSONArray getAllApply(Integer caaccount);
    JSONArray listAllDone(Integer caaccount) throws Exception;

    JSONArray getAlearyDoneApply(Integer caaccount);

    JSONArray getNoneDoneApply(Integer caaccount);
}
