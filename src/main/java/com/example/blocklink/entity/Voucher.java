package com.example.blocklink.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("voucher")
public class Voucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Expiration_time")
    private LocalDate expirationTime;

    private Integer grade;

    private Integer userBelong;
}
