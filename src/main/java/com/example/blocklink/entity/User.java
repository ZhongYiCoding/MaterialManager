package com.example.blocklink.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer account;

    private Integer password;
    //1 common 2 CA  3 WO
    private Integer type;

    @TableField("ID")
    private String address;


}
