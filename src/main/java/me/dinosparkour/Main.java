package me.dinosparkour;

import me.dinosparkour.commands.Commands;
import me.dinosparkour.commands.EvalCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException, RateLimitedException {
        new JDABuilder(AccountType.CLIENT)
                .setToken(Info.TOKEN)
                .addListener(new Commands())
                .addListener(new EvalCommand())
                .setAudioEnabled(false)
                .setBulkDeleteSplittingEnabled(false)
                .buildAsync();
    }
}