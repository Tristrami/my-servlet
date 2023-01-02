package com.seamew.httpServer.entity.request;

public class RequestBody
{
    private String content;

    public RequestBody() {}

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return this.content;
    }
}
