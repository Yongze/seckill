package org.seckill.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by yw850 on 6/11/2017.
 */
public class SuccessKilled {
    @Setter @Getter private long  seckillId;
    @Setter @Getter private long userPhone;
    @Setter @Getter private short state;
    @Setter @Getter private Date createTime;
//    变通
//    多对一
    @Setter @Getter private Seckill seckill;

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
