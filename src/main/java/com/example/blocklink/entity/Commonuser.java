package com.example.blocklink.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("commonuser")
public class Commonuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Information_authentication")
    private Integer informationAuthentication;

    private String organization;

    private Integer voucherId;

    private String name;

    private Integer account;


}
