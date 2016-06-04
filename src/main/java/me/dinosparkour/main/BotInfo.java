package me.dinosparkour.main;

import org.json.JSONObject;

import java.io.IOException;

public class BotInfo {

    private static final JSONObject config = Configurator.getConfig();
    private static final String email = config.getString("email");
    private static final String password = config.getString("password");
    private static final String key2fa = config.getString("key2fa");
    public static String AUTHOR_ID;
    private static String prefix = config.getString("prefix");

    static String getEmail() {
        return BotInfo.email;
    }

    static String getPassword() {
        return BotInfo.password;
    }

    public static String getPrefix() {
        return BotInfo.prefix;
    }

    public static void setPrefix(String s) {

        try {
            Configurator.write(Configurator.ConfigKey.PREFIX, s);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        prefix = s;
    }

    static String getKey2FA() {
        return BotInfo.key2fa.replace(" ", "");
    }
}