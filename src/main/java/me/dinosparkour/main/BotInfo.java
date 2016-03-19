package me.dinosparkour.main;

import net.dv8tion.jda.JDAInfo;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.concurrent.TimeUnit;

/**
 * @author Dinos
 * @since 19/03/2016
 **/
public class BotInfo {

    private static String version = "3.19";
    private static JSONObject config = Configurator.getConfig();
    private static String email = config.getString("email");
    private static String password = config.getString("password");
    private static String prefix = config.getString("prefix");

    public static String getVersion() {
        return BotInfo.version;
    }

    public static String getAuthorId() {
        return "98457903660269568";
    }

    protected static String getEmail() {
        return BotInfo.email;
    }

    protected static String getPassword() {
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

    public static String getLibrary() {
        return "JDA #" + JDAInfo.VERSION;
    }

    public static String getUptime() {
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long uptime = rb.getUptime();

        long days = TimeUnit.MILLISECONDS.toDays(uptime);
        uptime -= TimeUnit.DAYS.toMillis(days);

        long hours = TimeUnit.MILLISECONDS.toHours(uptime);
        uptime -= TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(uptime);
        uptime -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(uptime);

        StringBuilder uptimeMsg = new StringBuilder();
        if(days != 1L)
            uptimeMsg.append(days + " days ");
        else
            uptimeMsg.append("1 day ");

        if(hours != 1L)
            uptimeMsg.append(hours + " hours ");
        else
            uptimeMsg.append("1 hour ");

        if(minutes != 1L)
            uptimeMsg.append(minutes + " minutes ");
        else
            uptimeMsg.append("1 minute ");

        if(seconds != 1L)
            uptimeMsg.append(seconds + " seconds");
        else
            uptimeMsg.append("1 second");

        return uptimeMsg.toString();
    }
}