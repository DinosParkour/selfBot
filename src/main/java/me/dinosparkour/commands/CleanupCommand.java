package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.CommandImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class CleanupCommand extends CommandImpl {

    @Override
    public void execute(String[] args, MessageReceivedEvent e, MessageSender chat) {
        int amount;
        if (args.length > 0)
            try {
                amount = Integer.parseInt(args[0]);
                if (amount > 50 || amount <= 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                chat.update("**That's not a valid amount!** [1-50]");
                return;
            }
        else amount = 10;

        e.getChannel().getHistory().retrievePast(amount + 1).queue(history -> {
            if (history == null) {
                chat.update("*There are no messages to delete!*");
                return;
            }

            history.stream()
                    .filter(msg -> msg.getAuthor().equals(e.getAuthor()))
                    .forEach(msg -> msg.deleteMessage().queue());
        });
    }

    @Override
    public List<String> getAlias() {
        return Arrays.asList("cleanup", "prune", "purge");
    }
}