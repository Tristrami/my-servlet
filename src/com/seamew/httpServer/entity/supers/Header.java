package com.seamew.httpServer.entity.supers;

import com.seamew.httpServer.utils.FormatUtil;

import java.util.Map;

/**
 * 请求头和响应头的父类
 */

public class Header
{
    private Map<String, String> headers;

    public Header() {}

    public Header(Map<String, String> headers)
    {
        this.headers = headers;
    }

    public Map<String, String> headers()
    {
        return headers;
    }

    public void setProperty(String key, String value)
    {
        if (this.headers.containsKey(key))
        {
            this.headers.replace(key, value);
        }
        else
        {
            this.headers.put(key, value);
        }
    }

    public void setHeaders(Map<String, String> headers)
    {
        this.headers = headers;
    }

    @Override
    public String toString()
    {
        return FormatUtil.getHeaderString(this);
    }
}
