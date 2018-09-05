package com.netposa.rom.service.zimg.conf;


import com.netposa.rom.common.zimg.ZimgClient;
import com.netposa.rom.service.zimg.impl.DefaultDeleteCallBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Autowired
    ZimgConf zimgConf;

    @Bean
    public ZimgClient zimgClient(){
        ZimgClient zimgClient = new ZimgClient(zimgConf.getServerUrl());
        zimgClient.setDeleteCallBack(new DefaultDeleteCallBack());
        return zimgClient;
    }

}
