package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by yw850 on 6/14/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
        })
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() throws Exception {
        int id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    //集成测试代码完整逻辑，注意可重复执行。
    //把业务允许的异常放在try/catch里面，不去向上抛给Junit，当Junit接到异常的时候会认为是集成测试失败
    //集成测试业务的完整性
    @Test
    public void seckillLogic() throws Exception {
        int id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()){
            logger.info("exposer={}", exposer);
            long phone = 13502171129L;
            String md5 = exposer.getMd5();
            try{
                SeckillExecution execution = seckillService.executeSeckill(id, phone,md5);
                logger.info("result={}", execution);
            }catch (RepeatKillException repeatKillExecption){
                logger.error(repeatKillExecption.getMessage());
            }catch (SeckillCloseException seckillCloseExecption){
                logger.error(seckillCloseExecption.getMessage());
            }
        }else{
            //秒杀未开启
            logger.warn("exposer={}", exposer);
        }

        //Exposer{exposed=true, md5='d7ee6508b4f120fc49f223f58795991f', seckillId=1000, now=0, start=0, end=0}
    }

    @Test
    public void executeSeckillProcedure(){
        long seckillId = 1002;
        long phone = 13680111011L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.info(execution.getStateInfo());

        }
    }
}