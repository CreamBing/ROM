package com.netposa.rom.access.websocket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>Title: WebSocketOperation</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/8/20
 * @Company 东方网力
 */
@Component
public class WebSocketOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketOperation.class);

    private SocketIOServer server;

    public void init(SocketIOServer server) {
        this.server = server;
    }

    public int getSocketClientSize(){
        return server.getAllClients().size();
    }

    public Configuration getConf(){
        return server.getConfiguration();
    }


}
