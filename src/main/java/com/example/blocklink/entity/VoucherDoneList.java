package com.example.blocklink.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("voucher")
public class VoucherDoneList implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer account;
    private String context;
    private Integer type;
}
