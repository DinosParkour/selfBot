package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.CommandImpl;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.Presence;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StatusCommand extends CommandImpl {

    @Override
    public void execute(String[] args, MessageReceivedEvent e, MessageSender chat) {
        String allArgs = String.join(" ", Arrays.asList(args));
        if (allArgs.equalsIgnoreCase("list")) {
            chat.update("Valid Statuses: `online`, `idle`, `dnd`, `invisible`, `offline`");
            return;
        }

        Presence presence = e.getJDA().getPresence();
        OnlineStatus status = OnlineStatus.fromKey(allArgs);
        switch (status) {
            case UNKNOWN:
                chat.update("Please provide a valid status!");
                break;

            default:
                presence.setStatus(status);
                chat.update("Set the status to `" + status.getKey() + "`");
                break;
        }
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("status");
    }
}