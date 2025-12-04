package cn.pinellia.wrech.starter.dynamic.config.cente.type.common;

/**
 * @Author BanXia
 * @description: 常量
 * @Date 2025/12/5 01:13
 */
public class Constants {

    public final static String DYNAMIC_CONFIG_CENTER_REDIS_TOPIC = "DYNAMIC_CONFIG_CENTER_REDIS_TOPIC_";

    public final static String SYMBOL_COLON = ":";

    public final static String LINE = "_";

    public static String getTopic(String application) {
        return DYNAMIC_CONFIG_CENTER_REDIS_TOPIC + application;
    }

}
