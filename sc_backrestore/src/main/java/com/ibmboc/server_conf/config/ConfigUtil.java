package com.ibmboc.server_conf.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <类简介><br>
 * <p/>
 * <类详细描述><br>
 * <p/>
 * Created by IntelliJ IDEA.
 * User: MeiZhi
 * Date: 14-10-1
 * Time: 下午4:26
 */
public class ConfigUtil {

    static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
    private static String filePath = "backrestore-config.properties";

    public static String get(String key) {
        Properties props = new Properties();
        InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream(filePath);
        String value = null;
        try {
            props.load(in);
            value = props.getProperty(key);
        } catch (IOException e) {
            logger.error("读取配置文件{}失败!", filePath, e);
        }
        return value;
    }
}
