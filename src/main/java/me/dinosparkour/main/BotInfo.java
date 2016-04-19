package me.dinosparkour.main;

import org.json.JSONObject;

import java.io.IOException;

/**
 * @author Dinos
 * @since 19/03/2016
 **/
public class BotInfo {

    private static final JSONObject config = Configurator.getConfig();
    private static final String email = config.getString("email");
    private static final String password = config.getString("password");
    private static String prefix = config.getString("prefix");

    public static final String AUTHOR_ID = "98457903660269568";

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
            Configurator.write("prefix", s);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        prefix = s;
    }
}