package com.seamew.httpServer.entity.server;

import com.seamew.httpServer.utils.PropertiesUtil;

/**
 * 保存服务器的配置信息, 注意所有字段均为字符串
 * SERVER_IP_ADDRESS: 服务器 IP 地址
 * SERVER_PORT: 服务器端口号
 */

public class ServerConfig
{
    public static String SERVER_IP_ADDRESS;
    public static String SERVER_PORT;

    static
    {
        PropertiesUtil.setFileds("com/seamew/config/serverConfig.properties", ServerConfig.class, null);
    }
}
