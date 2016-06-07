package me.dinosparkour.main;

import me.dinosparkour.commands.Commands;
import me.dinosparkour.commands.EvalCommand;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.client.JDAClientBuilder;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA jda = new JDAClientBuilder()
//                .setEmail(BotInfo.getEmail())
//                .setPassword(BotInfo.getPassword())
//                .setCode(new Totp(BotInfo.getKey2FA()).now())
                .setClientToken(BotInfo.getToken())
                .addListener(new Commands())
                .addListener(new EvalCommand())
                .setAudioEnabled(false)
                .buildBlocking();

        jda.getAccountManager().setIdle(true);
        BotInfo.AUTHOR_ID = jda.getSelfInfo().getId();
    }
}