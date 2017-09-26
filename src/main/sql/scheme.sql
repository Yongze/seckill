# 数据库初始化脚本
#创建数据库
CREATE DATABASE seckill;
#使用数据库
use seckill;
#创建数据库秒杀表
CREATE table seckill(
  `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存',
  `name` VARCHAR(127) NOT NULL COMMENT '商品名称',
  `number` INT NOT NULL COMMENT '库存数量',
  `start_time` TIMESTAMP NOT NULL,
  `end_time` TIMESTAMP NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE = innoDB AUTO_INCREMENT=1000 DEFAULT CHARSET = utf8 COMMENT ='秒杀库存表';

# 初始化数据
INSERT INTO seckill(name, number, start_time, end_time)
    VALUES
      ('$1000 AU for iPhone6s',100, '2016-11-11 00;00:00','2018-11-11 00:00:00'),
      ('$500 AU for iPad4',200, '2016-11-11 00;00:00','2018-11-11 00:00:00'),
      ('$300 AU for Samsung s8',100, '2016-11-11 00;00:00','2018-11-11 00:00:00'),
      ('$100 AU for Mac pro',100, '2016-11-11 00;00:00','2018-11-11 00:00:00');

# 秒杀成功明细表
# 用户登录认证相关信息
CREATE TABLE success_killed(
  `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品id',
  `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
  `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态：-1 无效 0：成功 1：已付款 2：已发货',
  `create_time` TIMESTAMP NOT NULL COMMENT '秒杀成功时间',
  PRIMARY KEY (seckill_id, user_phone),/* 联合主键*/
  KEY idx_create_time(create_time)
)ENGINE = innoDB DEFAULT CHARSET = utf8 COMMENT ='秒杀成功明细表';

# 为什么手写DDL
# 记录每次上线的DDL修改
# 上线V1.1
# ......
# 上线V1.2
