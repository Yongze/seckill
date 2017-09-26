package org.seckill.exception;

/**
 * 重复秒杀异常（运行期异常）
 * 运行期异常不需要收到try和catch
 * spring只接收运行期异常回滚策略
 * Created by yw850 on 6/12/2017.
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
