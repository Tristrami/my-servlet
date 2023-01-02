package com.seamew.httpServer.utils;

import com.seamew.httpServer.entity.request.Request;
import com.seamew.httpServer.entity.request.RequestBody;
import com.seamew.httpServer.entity.request.RequestHeader;
import com.seamew.httpServer.entity.request.RequestLine;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestUtil
{
    /**
     * 从 socket 中以输入流获取 http 请求的字符串, 并从中分离出请求行、请求头及请求体, 封装到 Request 对象中
     * @param socket 服务器 tcp 连接端口
     * @return 封装了请求行、请求头及请求体的 Request 对象
     */
    public static Request handle(Socket socket)
    {
        Request request = new Request();
        try
        {
            InputStream in = socket.getInputStream();
            String requestInfo = StreamUtil.readString(in, 1024);

            // 分离出请求体
            String[] split1 = requestInfo.split("\r\n\r\n");

            // 分离请求行和请求头
            String[] split2 = split1[0].split("\r\n");

            String body = split1.length == 1 ? "" : split1[1];
            String line = split2[0];
            String[] headers = Arrays.copyOfRange(split2, 1, split2.length - 1);

            request.setRequestLine(buildRequestLine(line));
            request.setRequestHeader(buildRequestHeader(headers));
            request.setRequestBody(buildRequestBody(body));
            return request;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析请求行字符串, 将请求的操作类型、url及协议封装到 RequestLine 对象中
     * @param requestLineStr 请求行字符串
     * @return 封装了请求行数据的 RequestLine 对象
     */
    public static RequestLine buildRequestLine(String requestLineStr)
    {
        //分离操作类型、URL及参数、协议
        String[] split1 = requestLineStr.split(" ");

        //分离URI、参数
        String[] split2 = split1[1].split("\\?");

        RequestLine rl = new RequestLine();
        rl.setOperation(split1[0]);
        rl.setUri(split2[0]);
        rl.setParamString(split2.length == 1 ? "" : split2[1]);
        rl.setParamMap(split2.length == 1 ? new LinkedHashMap<>() : buildParamMap(split2[1]));
        rl.setProtocol(split1[2]);
        return rl;
    }

    /**
     * 解析请求头字符串, 将请求头以键值对的形式封装到 Map 中
     * @param requestHeaderStrs 请求头字符串
     * @return 封装了请求头数据的 RequestHeader 对象
     */
    public static RequestHeader buildRequestHeader(String[] requestHeaderStrs)
    {
        Map<String, String> headers = new LinkedHashMap<>();
        for (String header : requestHeaderStrs)
        {
            String[] split = header.split(": ");
            headers.put(split[0], split[1]);
        }
        return new RequestHeader(headers);
    }

    /**
     * 将请求体字符串封装到 RequestBody 对象中
     * @param requestBodyStrs 请求体字符串
     * @return 封装了请求体数据的 RequestBody 对象
     */
    public static RequestBody buildRequestBody(String requestBodyStrs)
    {
        RequestBody rb = new RequestBody();
        rb.setContent(requestBodyStrs);
        return rb;
    }

    /**
     * 将请求行中 URI 后的参数键值对构建为一个 Map
     * @param paramString 参数字符串, 如 uri...?username=admin&password=123
     * @return 封装了请求行中所带参数对 Map
     */
    public static Map<String, String> buildParamMap(String paramString)
    {
        Map<String, String> mp = new LinkedHashMap<>();
        String[] keyValues = (paramString.contains("&") ? paramString.split("&") : new String[]{paramString});
        for (String pair : keyValues)
        {
            String[] keyValue = pair.split("=");
            mp.put(keyValue[0], keyValue[1]);
        }
        return mp;
    }
}
