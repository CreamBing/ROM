package com.netposa.rom.access.websocket.constants;

/**
 * <p>Title: RedisConstants</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/8/17
 * @Company 东方网力
 */
public class RedisConstants {

    /**
     * hash存储 hkey+robotCode+当前时间戳(表示这次在线时间)
     *
     */
    public static final String SOCKET_CLIENT_ONLINE_LIST = "socket_client_online_list";

    /**
     * hash存储 hkey+robotCode+socketioclient序列化
     *
     */
    public static final String SOCKET_CLIENT_LIST = "socket_client_list";


    /**
     * hash存储 hkey+robotCode+当前时间戳(表示离线时间)
     *
     */
    public static final String SOCKET_CLIENT_OFFLINE_LIST = "socket_client_offline_list";
}
