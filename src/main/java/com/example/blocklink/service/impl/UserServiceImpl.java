package com.example.blocklink.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blocklink.entity.CertificateAuthority;
import com.example.blocklink.entity.Commonuser;
import com.example.blocklink.entity.User;
import com.example.blocklink.entity.WelfareOrganizations;
import com.example.blocklink.mapper.CertificateAuthorityMapper;
import com.example.blocklink.mapper.CommonuserMapper;
import com.example.blocklink.mapper.UserMapper;
import com.example.blocklink.mapper.WelfareOrganizationsMapper;
import com.example.blocklink.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommonuserMapper commonuserMapper;
    @Autowired
    private CertificateAuthorityMapper certificateAuthorityMapper;
    @Autowired
    private WelfareOrganizationsMapper welfareOrganizationsMapper;

    @Override
    public User login(int acount, int password) {
        User userDTO = new User();
        userDTO.setAccount(acount);
        userDTO.setPassword(password);
        QueryWrapper<User> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("password",password);
        queryWrapper.eq("account",acount);
        userDTO = userMapper.selectOne(queryWrapper);

        //初始化账号
        String name=Integer.toString(userDTO.getAccount());

        return userDTO;
    }

    @Override
    public boolean changePassword(int acount, int password,int newpassword) {
        QueryWrapper<User> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("account",acount);
        queryWrapper.eq("password",password);
        User userDTO = new User();
        userDTO = userMapper.selectOne(queryWrapper);
        if(userDTO == null)return false;
        userDTO.setPassword(newpassword);
        int result = userMapper.update(userDTO,queryWrapper);
        if(result == 1)return true;
        else return false;
    }

    public String pemAccountFilePath="xxx";
    @Override
    public boolean register(int acount, int password, int role) {
        User user = new User();
        user.setPassword(password);
        user.setAccount(acount);
        user.setType(role);

        //生成随机账号并保存
        CryptoSuite cryptoSuite = new CryptoSuite(CryptoType.ECDSA_TYPE);
        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair();
        //1. 数据库保存合约地址
        user.setAddress(cryptoKeyPair.getAddress());
        //2. 本地创建用户id目录来分别存储自己的pem文件
        String mkDirectoryPath = pemAccountFilePath+user.getAccount().toString();
        if (mkDirectory(mkDirectoryPath)) {
            System.out.println(mkDirectoryPath + "建立完成");
        } else {
            System.out.println(mkDirectoryPath + "建立失败！");
        }
        //本地保存
        cryptoKeyPair.storeKeyPairWithPem(pemAccountFilePath);
        String accountAddress = cryptoKeyPair.getAddress();

        int r = userMapper.insert(user);



        //1 common 2 CA  3 WO
        if(role==1){
            Commonuser commonuser = new Commonuser();
            commonuser.setAccount(acount);
            commonuserMapper.insert(commonuser);
        } else if (role == 2) {
            CertificateAuthority certificateAuthority = new CertificateAuthority();
            certificateAuthority.setAccount(acount);
            certificateAuthorityMapper.insert(certificateAuthority);
        }else {
            WelfareOrganizations welfareOrganizations = new WelfareOrganizations();
            welfareOrganizations.setAccount(acount);
            welfareOrganizationsMapper.insert(welfareOrganizations);
        }
        if(r==1)return true;
        else return false;
    }
    public boolean mkDirectory(String path) {
        File file = null;
        try {
            file = new File(path);
            if (!file.exists()) {
                return file.mkdirs();
            }
            else{
                return false;
            }
        } catch (Exception e) {
        } finally {
            file = null;
        }
        return false;
    }
}
