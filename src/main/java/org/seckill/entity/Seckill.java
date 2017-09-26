package org.seckill.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by yw850 on 6/11/2017.
 */
public class Seckill {
    @Setter @Getter private long seckillId;
    @Setter @Getter private String name;
    @Setter @Getter  private int number;
    @Setter @Getter  private Date startTime;
    @Setter @Getter  private Date endTime;
    @Setter @Getter private Date createTime;

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
