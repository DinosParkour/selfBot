package me.dinosparkour.commands.impls;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class SimpleCommandImpl extends CommandImpl {

    protected abstract String getMesasge();

    @Override
    public void execute(String[] args, MessageReceivedEvent e, MessageSender chat) {
        chat.update(getMesasge());
    }

}