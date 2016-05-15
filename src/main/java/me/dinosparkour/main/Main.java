package me.dinosparkour.main;

import me.dinosparkour.commands.Commands;
import me.dinosparkour.commands.EvalCommand;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.client.JDAClientBuilder;
import net.dv8tion.jda.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.IOException;

/**
 * @author Dinos
 * @since 19/03/2016
 **/
public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException, IOException, InterruptedException {
        JDA jda = new JDAClientBuilder()
                .setEmail(BotInfo.getEmail())
                .setPassword(BotInfo.getPassword())
                .addListener(new Commands())
                .addListener(new EvalCommand())
                .buildBlocking();

        jda.getAccountManager().setIdle(true);
        BotInfo.AUTHOR_ID = jda.getSelfInfo().getId();
    }
}