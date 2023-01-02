package com.seamew.httpServer.entity.response;

import com.seamew.httpServer.utils.PropertiesUtil;

import java.util.Map;

public class ResponseLine
{
    private String protocol;
    private String statusCode;
    private static final Map<String, String> statusDescriptions;

    public ResponseLine() {}

    public ResponseLine(String statusCode)
    {
        this.protocol = ResponseConfig.DEFAULT_PROTOCOL;
        this.statusCode = statusCode;
    }

    public ResponseLine(String protocol, String statusCode)
    {
        this.protocol = protocol;
        this.statusCode = statusCode;
    }

    static
    {
        statusDescriptions = PropertiesUtil.toMap(ResponseConfig.STATUS_CONFIG_PATH);
    }

    public String getProtocol()
    {
        return protocol;
    }

    public void setProtocol(String protocol)
    {
        this.protocol = protocol;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }

    public String statusDescription()
    {
        String s = statusDescriptions.get(this.statusCode);
        return s == null ? "" : s;
    }

    @Override
    public String toString()
    {
        return this.protocol + ' ' + this.statusCode + ' ' + this.statusDescription();
    }
}
