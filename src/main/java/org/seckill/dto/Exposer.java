package org.seckill.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 暴露秒杀地址DTO
 * Created by yw850 on 6/12/2017.
 */
public class Exposer {
    //是否开启秒杀
    @Setter @Getter private boolean exposed;
    @Setter @Getter   private String md5;
    @Setter @Getter   private long seckillId;
    //系统当前时间(毫秒)
    @Setter @Getter   private long now;
    @Setter @Getter  private long start;
    @Setter @Getter    private long end;

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, long now, long start, long end) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
