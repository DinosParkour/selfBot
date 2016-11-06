package me.dinosparkour.utils;

import java.util.concurrent.TimeUnit;

public class MessageUtil {

    public static String filter(String msgContent) {
        return msgContent.length() > 2000
                ? "*The output message is over 2000 characters!*"
                : msgContent.replace("@everyone", "@\u180Eeveryone").replace("@here", "@\u180Ehere");
    }

    public static String formatTime(long time) {
        long days = TimeUnit.MILLISECONDS.toDays(time);
        time -= TimeUnit.DAYS.toMillis(days);

        long hours = TimeUnit.MILLISECONDS.toHours(time);
        time -= TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
        time -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(time);

        return (days > 0 ? days + " " + (days != 1L ? "days" : "day") + " " : "") +
                (hours > 0 ? hours + " " + (hours != 1L ? "hours" : "hour") + " " : "") +
                (minutes > 0 ? minutes + " " + (minutes != 1L ? "minutes" : "minute") + " " : "") +
                (seconds > 0 ? seconds + " " + (seconds != 1L ? "seconds" : "second") : "");
    }

    public static String stripFormatting(String s) {
        return s.replace("*", "\\*")
                .replace("`", "\\`")
                .replace("_", "\\_")
                .replace("~~", "\\~\\~")
                .replace(">", "\u180E>");
    }
}