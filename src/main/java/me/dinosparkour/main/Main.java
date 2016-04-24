package me.dinosparkour.main;

import me.dinosparkour.commands.Commands;
import me.dinosparkour.commands.EvalCommand;
import me.dinosparkour.commands.Logger;
import net.dv8tion.jda.JDABuilder;
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

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws LoginException, IOException {

        File parentFolder = new File("logs");
        parentFolder.mkdir();
        String logFile = new SimpleDateFormat("dd.MM.yyyy").format(new Date()) + ".log";
        out = new File(parentFolder.getPath(), logFile);
        out.createNewFile();

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
        BotInfo.AUTHOR_ID = e.getJDA().getSelfInfo().getId();

        try {
            SimpleLog.addFileLogs(out, null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}