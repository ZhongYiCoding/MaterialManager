package com.example.blocklink.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("voucher_approve")
public class VoucherApprove  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer state;
    private Integer account;
    private Integer caaccount;
    private String context;
    private Timestamp datetime;
    public Timestamp getDatetime(){
        return this.datetime;
    }
}
