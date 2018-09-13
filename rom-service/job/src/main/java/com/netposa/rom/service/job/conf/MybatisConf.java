package com.netposa.rom.service.job.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.netposa.rom.dao.zimg.mysql"})
public class MybatisConf {
}
