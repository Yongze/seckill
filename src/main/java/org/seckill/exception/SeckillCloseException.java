package org.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by yw850 on 6/12/2017.
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
