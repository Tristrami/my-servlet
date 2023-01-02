package com.seamew.httpServer.entity.request;

import com.seamew.httpServer.utils.RequestUtil;

import java.net.Socket;

/**
 * Request 对象封装了http请求的请求行、请求头及请求体
 * RequestLine 封装请求行
 * RequestHeader 封装请求头
 * RequestBody 封装请求体
 */

public class Request
{
    private RequestLine requestLine;
    private RequestHeader requestHeader;
    private RequestBody requestBody;

    public Request() {}

    public static Request getRequest(Socket socket)
    {
        return RequestUtil.handle(socket);
    }

    public RequestLine getRequestLine()
    {
        return requestLine;
    }

    public void setRequestLine(RequestLine requestLine)
    {
        this.requestLine = requestLine;
    }

    public RequestHeader getRequestHeader()
    {
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader)
    {
        this.requestHeader = requestHeader;
    }

    public RequestBody getRequestBody()
    {
        return requestBody;
    }

    public void setRequestBody(RequestBody requestBody)
    {
        this.requestBody = requestBody;
    }

    @Override
    public String toString()
    {
        return requestLine.toString() + "\r\n" +
               requestHeader.toString() + "\r\n\r\n" +
               requestBody.toString();
    }
}
