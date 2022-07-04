package com.example.blocklink.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("request_approve")
public class RequestApprove implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer state;
    private Integer account;

    private Integer woaccount;
    private String data;
    private Integer money;
    private Integer medical;
    private Integer food;
}
