package com.netposa.rom.ctrl.zimg.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.netposa.rom.dao.zimg.mysql"})
public class MybatisConf {
}
