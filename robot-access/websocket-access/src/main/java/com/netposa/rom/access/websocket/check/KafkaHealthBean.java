/**
 * creambing.com Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */


package com.netposa.rom.access.websocket.check;

import lombok.Data;

/**
 * Class Name:KafkaHealthBean
 * Description:kafka监控实体
 *
 * @author Bing
 * @create 2018-08-19  16:33
 * @version v1.0
 */
@Data
public class KafkaHealthBean {

    private String status;
    private Details details;

    @Data
    static class Details{
        private String version;
        private String connectionCount;
        private long lastHeartbeatSeconds;
    }
}
