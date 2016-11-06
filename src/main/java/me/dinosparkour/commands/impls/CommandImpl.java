package me.dinosparkour.commands.impls;

import me.dinosparkour.Bot;
import me.dinosparkour.Info;
import me.dinosparkour.utils.MessageUtil;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public abstract class CommandImpl extends ListenerAdapter {

    protected abstract void execute(String[] args, MessageReceivedEvent e, MessageSender chat);

    protected abstract List<String> getAlias();

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor() == null || !e.getAuthor().getId().equals(Bot.getAuthorId()))
            return; // Ignore messages not sent by the selfBot's author

        if (!isValidCommand(e.getMessage()))
            return; // Ignore message if it's not a command or sent by a bot

        execute(commandArgs(e.getMessage()), e, new MessageSender(e));
    }

    private boolean isValidCommand(Message msg) {
        if (!msg.getRawContent().startsWith(Info.PREFIX))
            return false; // It's not a command if it doesn't start with our prefix
        String cmdName = msg.getRawContent().substring(Info.PREFIX.length());
        if (cmdName.contains(" "))
            cmdName = cmdName.substring(0, cmdName.indexOf(" ")); // If there are parameters, remove them
        return getAlias().contains(cmdName.toLowerCase());
    }

    private String[] commandArgs(Message msg) {
        String noPrefix = msg.getRawContent().substring(Info.PREFIX.length());
        if (!noPrefix.contains(" ")) // No whitespaces -> No args
            return new String[]{};
        return noPrefix.substring(noPrefix.indexOf(" ") + 1).split("\\s+");
    }

    protected class MessageSender {
        private final MessageReceivedEvent event;

        MessageSender(MessageReceivedEvent event) {
            this.event = event;
        }

        public void update(String msgContent) {
            event.getMessage().editMessage(MessageUtil.filter(msgContent)).queue();
        }
    }
}