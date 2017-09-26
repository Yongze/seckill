package org.seckill.dto;

import lombok.Getter;
import lombok.Setter;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;

/**
 * 封装秒杀执行后的结果
 * Created by yw850 on 6/12/2017.
 */
public class SeckillExecution {

    @Getter @Setter private long seckillId;
    //秒杀执行结果状态
    @Getter @Setter private SeckillStatEnum state;
    //表示状态
    @Getter @Setter private String stateInfo;
    //秒杀成功对象
    @Getter @Setter private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = statEnum;
        this.stateInfo = statEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
        this.seckillId = seckillId;
        this.state = statEnum;
        this.stateInfo = statEnum.getStateInfo();
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
