package com.seamew.httpServer.entity.server;

import com.seamew.httpServer.entity.request.Request;
import com.seamew.httpServer.entity.response.Response;
import com.seamew.httpServer.utils.StreamUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

/**
 * Server 对象用于接收请求并自动构建响应对象来响应请求
 * Server 对象接收到请求后会建立连接, 并保存:
 * 当前连接的 Socket 对象
 * 根据该 Socket 对象中的输入流构建的 Request 对象
 * 根据 Request 对象构建的 Response 对象
 * ⚠️: 再次调用 listen() 方法前要先调用 close() 方法关闭连接, 否则可能无法接收到新的请求
 */

public class Server
{
    private ServerSocket listener;
    private Socket currentSocket;
    private Request currentRequest;
    private Response currentResponse;

    private Server(SocketAddress endPoint)
    {
        try
        {
            this.listener = new ServerSocket();
            this.listener.bind(endPoint);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Server createServer()
    {
        InetAddress inetAddress = null;
        InetSocketAddress inetSocketAddress = null;
        try
        {
            inetAddress = InetAddress.getByName(ServerConfig.SERVER_IP_ADDRESS);
            inetSocketAddress = new InetSocketAddress(inetAddress, Integer.parseInt(ServerConfig.SERVER_PORT));
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        return new Server(inetSocketAddress);
    }

    public Socket listen()
    {
        Socket socket = null;
        try
        {
            socket = this.listener.accept();
            this.currentSocket = socket;
            this.currentRequest = Request.getRequest(socket);
            this.currentResponse = Response.buildResponse(this.currentRequest);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return socket;
    }

    public void response()
    {
        try
        {
            OutputStream out = this.currentSocket.getOutputStream();
            StreamUtil.writeString(this.currentResponse.toString(), out, true);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void close()
    {
        try
        {
            this.currentSocket.close();
            this.currentRequest = null;
            this.currentResponse = null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ServerSocket getListener()
    {
        return listener;
    }

    public Socket getCurrentSocket()
    {
        return currentSocket;
    }

    public Request getCurrentRequest()
    {
        return currentRequest;
    }

    public Response getCurrentResponse()
    {
        return currentResponse;
    }
}
