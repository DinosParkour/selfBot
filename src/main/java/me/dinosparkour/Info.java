package me.dinosparkour;

import me.dinosparkour.utils.MessageUtil;

import java.lang.management.ManagementFactory;

public class Info {

    public static final String PREFIX = "/";

    private static final Config CONFIG = new Config();
    static final String TOKEN = CONFIG.getValue("token");

    public static String getUptime() {
        return MessageUtil.formatTime(ManagementFactory.getRuntimeMXBean().getUptime());
    }
}