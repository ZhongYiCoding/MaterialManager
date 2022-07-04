package com.example.blocklink.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("contribution_approve")
public class ContributionAapprove implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer state;
    private Integer account;
    private Integer woaccount;
    private String data;
    private Integer money;
    private Integer medical;
    private Integer food;
}



