package com.seamew.httpServer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtil
{
    /**
     * 将输入流中的所有字节全部一次读出, 并解析为字符串
     * @param in 输入流
     * @param bufferSize 存放输入流中所有字节的缓冲区的大小
     * @return 从字节流中解析出的字符串
     */
    public static String readString(InputStream in, int bufferSize)
    {
        StringBuilder sb = new StringBuilder(64);
        try
        {
            byte[] buf = new byte[bufferSize];
            int readLen = in.read(buf);
            sb.append(new String(buf, 0, readLen));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 将输入流中的字节分若干次读出, 并解析为字符串
     * @param in 输入流
     * @return 从字节流中解析出的字符串
     */
    public static String readString(InputStream in)
    {
        StringBuilder sb = new StringBuilder(64);
        try
        {
            byte[] buf = new byte[1024];
            int readLen;
            while ((readLen = in.read(buf)) != -1)
            {
                sb.append(new String(buf, 0, readLen));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 将字符串以字节流方式写入到输出流中
     * @param content 要写入的字符串
     * @param out 输出流
     * @param flush 是否在字符串写入该输出流后立即将输出流中的所有内容写出到目的地, 此操作将清空输出流
     */
    public static void writeString(String content, OutputStream out, boolean flush)
    {
        try
        {
            out.write(content.getBytes());
            if (flush) out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 将输出流中的所有内容写出到目的地, 此操作将清空输出流
     * @param out 输出流
     */
    public static void flush(OutputStream out)
    {
        try
        {
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void closeInputStream(InputStream in)
    {
        if (in != null)
        {
            try
            {
                in.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void closeOutputStream(OutputStream out)
    {
        if (out != null)
        {
            try
            {
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
