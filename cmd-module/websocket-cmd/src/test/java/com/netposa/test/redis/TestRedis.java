package com.netposa.test.redis;

import com.netposa.rom.WebsocketCmdApplication;
import com.netposa.rom.common.redis.conf.RedisUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Title: TestRedis</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/8/17
 * @Company 东方网力
 */
@SpringBootTest(classes = WebsocketCmdApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestRedis {

    @Autowired
    RedisUtil redisUtil;


}
