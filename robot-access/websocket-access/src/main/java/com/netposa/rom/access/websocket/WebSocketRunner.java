package com.netposa.rom.access.websocket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.transport.NamespaceClient;
import com.netposa.rom.access.websocket.conf.AppConf;
import com.netposa.rom.access.websocket.constants.RedisConstants;
import com.netposa.rom.common.redis.conf.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: WebSocketRunner</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/8/16
 * @Company 东方网力
 */
@Component
public class WebSocketRunner implements CommandLineRunner {

    private static Logger LOGGER = LoggerFactory.getLogger(WebSocketRunner.class);

    //上下线使用
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AppConf appConfig;

    @Autowired
    private WebSocketOperation webSocketOperation;

    @Override
    public void run(String... strings) {
        try {
            Configuration config = new Configuration();
            config.setPort(appConfig.getSocketPort());
            final SocketIOServer server = new SocketIOServer(config);
            webSocketOperation.init(server);
            //TODO 后期改为注解方式实现
            server.addConnectListener(socketIOClient -> {
                String robotCoce = getRobotCode(socketIOClient);
                if (StringUtils.isBlank(robotCoce)) {
                    LOGGER.error("robotCoce is null socketIOClient disconnect------------------");
                    socketIOClient.disconnect();
                    return;
                }
                socketIOClient.set("robotCode", robotCoce);
                socketIOClient.joinRoom(robotCoce);
                //设置socket连接方式的总数列表
                redisUtil.hSet(RedisConstants.SOCKET_CLIENT_LIST,robotCoce,System.currentTimeMillis());
                //将当前socket序列化到redis的online列表中 TODO 这是无法被序列化的
//                redisUtil.hSet(RedisConstants.SOCKET_CLIENT_ONLINE_LIST,robotCoce,socketIOClient);
                //删除离线列表中的记录，如果有的话
                redisUtil.hDel(RedisConstants.SOCKET_CLIENT_OFFLINE_LIST,robotCoce);
                Collection<SocketIOClient> clientCollection = server.getAllClients();
                if (clientCollection != null) {
                    LOGGER.info("当前socket连接数:{}", clientCollection.size());
                }
            });
            server.addDisconnectListener(socketIOClient -> {
                NamespaceClient namespaceClient = (NamespaceClient) socketIOClient;
                String robotCoce = namespaceClient.getBaseClient().getStore().get("robotCode");
                if (StringUtils.isBlank(robotCoce)) {
                    LOGGER.error("socket连接的机器人中没有robotCode");
                    socketIOClient.disconnect();
                    return;
                }
                socketIOClient.disconnect();
                //删除redis的online列表中
                redisUtil.hDel(RedisConstants.SOCKET_CLIENT_ONLINE_LIST,robotCoce);
                //新增一条离线记录
                redisUtil.hSet(RedisConstants.SOCKET_CLIENT_OFFLINE_LIST,robotCoce,System.currentTimeMillis());
                Collection<SocketIOClient> clientCollection = server.getAllClients();
                if (clientCollection != null) {
                    LOGGER.info("[{}]socketIOClient 断开连接!当前socket连接数:[{}]", robotCoce, clientCollection.size());
                }
            });
            server.addEventListener(appConfig.getSocketClientEvent(), String.class, (client, data, ackRequest) -> {
                String sa = client.getRemoteAddress().toString();
                //获取客户端连接的ip
                String clientIp = sa.substring(1, sa.indexOf(":"));
                //获取客户端url参数
                Map params = client.getHandshakeData().getUrlParams();
                System.out.println(clientIp + "：客户端：************" + data);
            });
            server.start();
        } catch (Exception e) {
            LOGGER.error("StartupRunner error", e);
            e.printStackTrace();
        }
    }

    private String getRobotCode(SocketIOClient socketIOClient) {
        Map<String, List<String>> urlParams = socketIOClient.getHandshakeData().getUrlParams();
        if (urlParams != null && urlParams.containsKey("robotCode")) {
            List<String> tokens = urlParams.get("robotCode");
            if (tokens != null && !tokens.isEmpty()) {
                return tokens.get(0);
            }
        }
        return null;
    }


    private String getToken(SocketIOClient socketIOClient) {
        Map<String, List<String>> urlParams = socketIOClient.getHandshakeData().getUrlParams();
        if (urlParams != null && urlParams.containsKey("token")) {
            List<String> tokens = urlParams.get("token");
            if (tokens != null && !tokens.isEmpty()) {
                return tokens.get(0);
            }
        }
        return null;
    }
}
