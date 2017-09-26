package org.seckill.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 使用枚举表示常量数据
 * Created by yw850 on 6/12/2017.
 */
public enum SeckillStatEnum {
    SUCCESS(1, "seckill success"),
    END(0, "seckill end"),
    REPEAT_KILL(-1, "REPEAT KILL"),
    INNER_ERROR(-2,"SYSTEM EXECPTION"),
    DATA_REWRITE(-3, "DATA_REWRITE");
    @Getter @Setter private int state;
    @Getter @Setter private String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static SeckillStatEnum stateOf(int index){
        for (SeckillStatEnum state: values()){
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
