package me.dinosparkour.main;

import me.dinosparkour.commands.Commands;
import me.dinosparkour.commands.EvalCommand;
import me.dinosparkour.commands.Logger;
import net.dv8tion.jda.client.JDAClientBuilder;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.dv8tion.jda.utils.SimpleLog;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dinos
 * @since 19/03/2016
 **/
public class Main extends ListenerAdapter {

    private static File out;

    public static void main(String[] args) throws LoginException, IOException {

        File parentFolder = new File("logs");
        if (!parentFolder.mkdir())
            System.err.println("Creating the log folder was unsuccessful!");
        else if (!new File(parentFolder.getPath(), new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + ".log").createNewFile())
            System.err.println("Creating the log file was unsuccessful!");

        new JDAClientBuilder()
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
        BotInfo.AUTHOR_ID = e.getJDA().getSelfInfo().getId();

        try {
            SimpleLog.addFileLogs(out, null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}