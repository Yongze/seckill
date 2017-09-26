package org.seckill.exception;

/**
 * 所有秒杀相关的业务异常
 * Created by yw850 on 6/12/2017.
 */
public class SeckillException extends RuntimeException{
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
