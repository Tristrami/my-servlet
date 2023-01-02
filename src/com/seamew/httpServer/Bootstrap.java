package com.seamew.httpServer;

import com.seamew.httpServer.entity.server.Server;

public class Bootstrap
{
    public static void main(String[] args)
    {
        Server server = Server.createServer();
        while (true)
        {
            server.listen();
            server.response();
            System.out.println(server.getCurrentSocket());
            System.out.println(server.getCurrentRequest());
            server.close();
        }
    }
}
