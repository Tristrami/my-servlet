package com.seamew.httpServer.entity.response;

import com.seamew.httpServer.entity.request.Request;
import com.seamew.httpServer.utils.ResponseUtil;

/**
 * Response 对象封装了 http 响应的响应行、响应头及响应体
 * 默认协议为 HTTP/1.1
 * 默认 Content-Type 为 text/html; charset=UTF-8
 * 响应的配置可在 responseConfig.properties 中修改
 * 响应头配置可在 responseHeader.properties 中修改
 */

public class Response
{
    private ResponseLine responseLine;
    private ResponseHeader responseHeader;
    private ResponseBody responseBody;

    public Response() {}

    public Response(ResponseLine responseLine, ResponseHeader responseHeader, ResponseBody responseBody)
    {
        this.responseLine = responseLine;
        this.responseHeader = responseHeader;
        this.responseBody = responseBody;
    }

    public static Response buildResponse(Request request)
    {
        return buildResponse(request, null, null);
    }

    public static Response buildResponse(Request request, String contentType)
    {
        return buildResponse(request, contentType, null);
    }

    public static Response buildResponse(Request request, String contentType, String protocol)
    {
        return ResponseUtil.build(request, contentType, protocol);
    }

    public ResponseLine getResponseLine()
    {
        return responseLine;
    }

    public void setResponseLine(ResponseLine responseLine)
    {
        this.responseLine = responseLine;
    }

    public ResponseHeader getResponseHeader()
    {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader)
    {
        this.responseHeader = responseHeader;
    }

    public ResponseBody getResponseBody()
    {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody)
    {
        this.responseBody = responseBody;
    }

    @Override
    public String toString()
    {
        return this.responseLine.toString() + "\r\n" +
               this.responseHeader.toString() + "\r\n\r\n" +
               this.responseBody.toString();
    }
}
