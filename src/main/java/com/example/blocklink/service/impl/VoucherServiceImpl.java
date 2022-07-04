package com.example.blocklink.service.impl;

import com.example.blocklink.entity.Voucher;
import com.example.blocklink.mapper.VoucherMapper;
import com.example.blocklink.service.IVoucherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher> implements IVoucherService {

}
