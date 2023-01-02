package com.seamew.httpServer.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil
{
    /**
     * 加载指定文件路径下的 properties 文件并封装在 Properties 对象中
     * @param path 文件的相对路径, 根目录为当前工程目录 src
     * @return Properties 对象
     */
    public static Properties get(String path)
    {
        Properties p = null;
        try (InputStream in = PropertiesUtil.class.getClassLoader()
                .getResourceAsStream(path))
        {
            p = new Properties();
            p.load(in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return p;
    }

    /**
     * 加载指定文件路径下的 properties 文件并封装在 Map 对象中
     * @param path 文件的相对路径, 根目录为当前工程目录 src
     * @return Map 对象
     */
    public static Map<String, String> toMap(String path)
    {
        return toMap(get(path));
    }

    /**
     * 将 Properties 对象中的数据封装在 Map 对象中
     * @param p Properties 对象
     * @return Map 对象
     */
    public static Map<String, String> toMap(Properties p)
    {
        Map<String, String> map = new LinkedHashMap<>();
        for (String key : p.stringPropertyNames())
        {
            String value = p.getProperty(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 将指定路径下的 .properties 文件读入到 Properties 对象中, 将对象 t 中
     * 字段名与 Properties 对象中键名 (key) 相同的字段赋值为该键对应的值 (value),
     * 若 t 为 null, 则对该类的静态字段赋值, 注意从文件读入的 Properties 对象中的
     * 键值均为字符串
     *
     * eg: .properties 中有 user=admin, 则将对象 t 中的 user 字段赋值为 admin,
     *     若 t 为 null, 则将类 T 中的静态字段 user 赋值为 admin
     *
     * @param configFilePath .properties 文件路径
     * @param clazz 类的 class 文件
     * @param t 赋值字段所属对象, 若为 null 则赋值类的静态字段
     * @param <T> 类型
     */
    public static <T> void setFileds(String configFilePath, Class<T> clazz, T t)
    {
        Properties config = get(configFilePath);
        try
        {
            for (Object key : config.keySet())
            {
                Field field = clazz.getField(key.toString());
                field.setAccessible(true);
                field.set(t, config.get(key));
            }
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 将 Map 对象中的映射关系以字符串的形式存储到指定路径的文件中
     * @param config Map 对象
     * @param desPath 存储路径, 注意这里是绝对路径
     */
    public static void save(Map<String, String> config, String desPath)
    {
        try (OutputStream out = new FileOutputStream(desPath))
        {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (String key : config.keySet())
            {
                sb.append(key).append('=').append(config.get(key))
                        .append(i == config.size() - 1 ? "" : '\n');
                i++;
            }
            StreamUtil.writeString(sb.toString(), out, true);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
