package com.seamew.httpServer.utils;

import com.seamew.httpServer.entity.supers.Header;

import java.util.Set;

/**
 * 用于字符串的格式化操作
 */

public class FormatUtil
{
    public static String getHeaderString(Header header)
    {
        StringBuilder sb = new StringBuilder(64);
        Set<String> keySet = header.headers().keySet();
        int i = 0;
        for (String key : keySet)
        {
            sb.append(key).append(": ").append(header.headers().get(key))
                    .append(i == keySet.size() - 1 ? "" : "\r\n");
            i++;
        }
        return sb.toString();
    }
}
