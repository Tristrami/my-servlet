package com.seamew.httpServer.entity.response;

import com.seamew.httpServer.utils.PropertiesUtil;

/**
 * 封装响应的配置信息, 其中的文件路径是相对路径, 根目录为当前项目工程目录(src)
 * 可以用 class.getClassLoader().getResourceAsStream(relativePath) 获取输入流来读取配置文件
 * 或先用 class.getResource("/").getPath() 获取当前工程根目录绝对路径, 再拼接上配置文件路径来读取
 */

public class ResponseConfig
{
    public static String HEADER_CONFIG_PATH;
    public static String STATUS_CONFIG_PATH;
    public static String MAPPER_CONFIG_PATH;
    public static String DEFAULT_PROTOCOL;

    static
    {
        PropertiesUtil.setFileds("com/seamew/config/responseConfig.properties", ResponseConfig.class, null);
    }
}
