package com.seamew.httpServer.entity.response;

public class ResponseBody
{
    private String content;

    public ResponseBody() {}

    public ResponseBody(String content)
    {
        this.content = content;
    }

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
        return this.getContent();
    }
}
