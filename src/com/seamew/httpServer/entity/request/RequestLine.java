package com.seamew.httpServer.entity.request;

import java.util.Map;

public class RequestLine
{
    public String operation;
    public String uri;
    public String paramString;
    public String protocol;
    public Map<String, String> paramMap;

    public RequestLine() {}

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public String getParamString()
    {
        return paramString;
    }

    public void setParamString(String paramString)
    {
        this.paramString = paramString;
    }

    public String getProtocol()
    {
        return protocol;
    }

    public void setProtocol(String protocol)
    {
        this.protocol = protocol;
    }

    public String getParameter(String key)
    {
        return this.paramMap.get(key);
    }

    public void setParamMap(Map<String, String> paramMap)
    {
        this.paramMap = paramMap;
    }

    @Override
    public String toString()
    {
        return this.operation + ' ' + this.uri +
                ("".equals(this.paramString) ? "" : ('?' + this.paramString)) +
                ' ' + this.protocol;
    }
}
