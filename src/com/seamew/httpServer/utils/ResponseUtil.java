package com.seamew.httpServer.utils;

import com.seamew.httpServer.entity.request.Request;
import com.seamew.httpServer.entity.response.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResponseUtil
{
    /**
     * 根据响应内容构建相应的响应对象
     * @param request 收到的http请求
     * @param contentType 响应数据的解析格式, 如 text/html; charset=UTF-8
     * @return 根据请求内容封装的响应对象
     */
    public static Response build(Request request, String contentType, String protocol)
    {
        String statusCode;
        String filePath = ResponseDispatcher.getFilePath(request.getRequestLine().getUri());

        if (filePath == null)
        {
            statusCode = "404";
            filePath = "/web/404.html";
        }
        else
        {
            statusCode = "200";
        }

        ResponseLine rl = protocol == null ? buildLine(statusCode) : buildLine(protocol, statusCode);
        ResponseHeader rh = buildHeader();
        ResponseBody rb = buildBody(ResponseUtil.class.getResource("/").getPath() + filePath);

        if (contentType != null) rh.setProperty("Content-Type", contentType);
        rh.setProperty("Content-Length", String.valueOf(rb.getContent().getBytes().length));
        return new Response(rl, rh, rb);
    }

    public static ResponseLine buildLine(String statusCode)
    {
        return new ResponseLine(statusCode);
    }

    /**
     * 根据协议及状态码构建响应行
     * @param protocol 响应协议
     * @param statusCode 响应状态码
     * @return 封装了响应行数据的 ResponseLine 对象
     */
    public static ResponseLine buildLine(String protocol, String statusCode)
    {
        return new ResponseLine(protocol, statusCode);
    }

    /**
     * 从配置文件 responseHeader.properties 中读取响应头并封装在 ResponseHeader 对象中
     * @return 封装了响应头数据的 ResponseHeader 对象
     */
    public static ResponseHeader buildHeader()
    {
        return new ResponseHeader(PropertiesUtil.toMap(ResponseConfig.HEADER_CONFIG_PATH));
    }

    /**
     * 以字符串形式读取指定路径文件内容并构建响应体
     * @param filePath 文件绝对路径
     * @return 封装了文件内容字符串的 ResponseBody 对象
     */
    public static ResponseBody buildBody(String filePath)
    {
        String s = null;
        try (InputStream in = new FileInputStream(filePath))
        {
            s = StreamUtil.readString(in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new ResponseBody(s);
    }
}
