package me.dinosparkour.main;

import me.dinosparkour.commands.Commands;
import me.dinosparkour.commands.EvalCommand;
import me.dinosparkour.commands.Logger;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

/**
 * @author Dinos
 * @since 19/03/2016
 **/
public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {

        new JDABuilder()
                .setEmail(BotInfo.getEmail())
                .setPassword(BotInfo.getPassword())
                .addListener(new Commands())
                .addListener(new EvalCommand())
                .addListener(new Main())
                .addListener(new Logger())
                .buildAsync();
    }

    @Override
    public void onReady(ReadyEvent e) {
        e.getJDA().getAccountManager().setIdle(true);
    }
}