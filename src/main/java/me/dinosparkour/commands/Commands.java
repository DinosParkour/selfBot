package me.dinosparkour.commands;

import me.dinosparkour.main.BotInfo;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * @author Dinos
 * @since 19/03/2016
 **/
public class Commands extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        JDA jda = e.getJDA();
        User author = e.getAuthor();
        Message message = e.getMessage();
        String msg = message.getContent();

        String prefix = BotInfo.getPrefix();

        if(!author.getId().equals(BotInfo.getAuthorId())
                || !msg.startsWith(prefix)) return;

        String[] args = msg.split("\\s+");
        String command = args[0].substring(prefix.length());
        String input = null;
        if(msg.contains(" "))
            input = msg.substring(msg.indexOf(' ')+1);

        switch (command.toLowerCase()) {
            case "game":
                if(input == null)
                    message.updateMessage("Currently playing: `" + jda.getSelfInfo().getCurrentGame() + "`");
                else if(input.equalsIgnoreCase("null")) {
                    jda.getAccountManager().setGame("");
                    message.updateMessage("Stopped playing.");
                } else {
                    jda.getAccountManager().setGame(input);
                    message.updateMessage("Now playing: `" + jda.getSelfInfo().getCurrentGame() + "`");
                }
                break;

            case "meme":
            case "nicememe":
                message.updateMessage("https://giphy.com/gifs/meme-meirl-315b275sfehgs");
                break;

            case "out":
            case "imout":
                message.updateMessage("http://i.imgur.com/KBNcZ.gif");
                break;

            case "nope":
            case "ohgodwhy":
                message.updateMessage("https://giphy.com/gifs/reaction-nope-oh-god-why-dqmpS64HsNvb2");
                break;

            case "prefix":
                if(input != null) {
                    BotInfo.setPrefix(input);
                    message.updateMessage("New Prefix `" + input + "`");
                } else
                    message.updateMessage("Current Prefix `" + prefix + "`");
                break;

            case "shrug":
                message.updateMessage("¯\\_(\u30c4)_/¯");
                break;
        }
    }
}