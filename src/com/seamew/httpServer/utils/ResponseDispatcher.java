package com.seamew.httpServer.utils;

import com.seamew.httpServer.entity.response.ResponseConfig;

import java.util.Map;

/**
 * 封装http请求中的url与响应文件路径的映射关系
 */

public class ResponseDispatcher
{
    private static final Map<String, String> MAPPER = PropertiesUtil.toMap(ResponseConfig.MAPPER_CONFIG_PATH);

    /**
     * 根据 http 请求中的 url 获取相应的响应文件路径
     * @param requestPath http 请求中的 url
     * @return 响应文件路径
     */
    public static String getFilePath(String requestPath)
    {
        return MAPPER.get(requestPath);
    }
}
