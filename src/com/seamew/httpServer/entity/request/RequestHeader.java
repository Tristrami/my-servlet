package com.seamew.httpServer.entity.request;

import com.seamew.httpServer.entity.supers.Header;

import java.util.Map;

/**
 * 请求头以键值对的形式封装在Map中
 */

public class RequestHeader extends Header
{
    private Map<String, String> headers;

    public RequestHeader() {}

    public RequestHeader(Map<String, String> headers)
    {
        super(headers);
    }
}