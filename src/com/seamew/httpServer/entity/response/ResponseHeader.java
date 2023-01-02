package com.seamew.httpServer.entity.response;

import com.seamew.httpServer.entity.supers.Header;

import java.util.Map;

/**
 * 响应头以键值对的形式封装在Map中
 */

public class ResponseHeader extends Header
{
    private Map<String, String> headers;

    public ResponseHeader() {}

    public ResponseHeader(Map<String, String> headers)
    {
        super(headers);
    }
}
