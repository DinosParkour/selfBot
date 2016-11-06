package me.dinosparkour;

import me.dinosparkour.commands.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {

    private static String authorId;

    public static String getAuthorId() {
        return authorId;
    }

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {

        new JDABuilder(AccountType.CLIENT)
                // Options
                .setAudioEnabled(false) // We don't utilise JDA's audio subsystem
                .setToken(Info.TOKEN) // Set the Authentication Token
                .setBulkDeleteSplittingEnabled(false) // Performance reasons

                // Listeners
                .addListener(new Bot())

                // Commands
                .addListener(new AppsCommand())
                .addListener(new BlockCommand())
                .addListener(new CringeCommand())
                .addListener(new CleanupCommand())
                .addListener(new DocsCommand())
                .addListener(new EvalCommand())
                .addListener(new EyesCommand())
                .addListener(new EyesOkCommand())
                .addListener(new GameCommand())
                .addListener(new GodWhyCommand())
                .addListener(new ImOutCommand())
                .addListener(new ItsTimeCommand())
                .addListener(new LeaveCommand())
                .addListener(new LennyCommand())
                .addListener(new NiceMemeCommand())
                .addListener(new RateLimitsCommand())
                .addListener(new StatusCommand())
                .addListener(new TriggeredCommand())
                .addListener(new UptimeCommand())

                // Login
                .buildAsync();
    }

    @Override
    public void onReady(ReadyEvent e) {
        authorId = e.getJDA().getSelfUser().getId();
        e.getJDA().getPresence().setStatus(OnlineStatus.INVISIBLE);
    }
}