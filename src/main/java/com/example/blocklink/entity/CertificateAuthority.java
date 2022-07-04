package com.example.blocklink.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("certificate_authority")
public class CertificateAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String information;

    private Integer account;


}
