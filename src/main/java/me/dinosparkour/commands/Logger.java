package me.dinosparkour.commands;

import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.time.format.DateTimeFormatter;

/**
 * @author Dinos
 * @since 19/03/2016
 **/
public class Logger extends ListenerAdapter {

    private static boolean enable = true;

    static boolean isEnabled() {
        return enable;
    }

    static void toggle(boolean b) {
        enable = b;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(!Logger.isEnabled()) return;

        String content = e.getMessage().getContent();
        String message = (content.contains("\n")) ? "\n" + content : content;
        System.out.printf("[%s] [%s#%s] %s: %s\n",
                e.getMessage().getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                e.getGuild().getName(),
                e.getChannel().getName(),
                e.getAuthor().getUsername(),
                message);
    }
}