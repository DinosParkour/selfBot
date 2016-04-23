package me.dinosparkour.commands;

import me.dinosparkour.main.BotInfo;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.MessageHistory;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import org.apache.commons.lang3.math.NumberUtils;

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

        if(!author.getId().equals(BotInfo.AUTHOR_ID)
                || !msg.startsWith(prefix)) return;

        String[] args = msg.split("\\s+");
        String command = args[0].substring(prefix.length());
        String input = null;
        if(msg.contains(" "))
            input = msg.substring(msg.indexOf(' ')+1);

        switch (command.toLowerCase()) {
            case "apps":
                message.updateMessage("<https://discordapp.com/developers/applications/me>"
                        +"\nMake sure you're logged in as yourself and not as your bot!");
                break;

            case "converter":
                message.updateMessage("https://github.com/DinosParkour/BotConverter");
                break;

            case "invite":
                message.updateMessage("https://discordapp.com/oauth2/authorize?client_id=APP_ID&scope=bot"
                        + "\n\nReplace `APP_ID` in that link with your bot's app id.");
                break;

            case "cleanup":
                int amount;

                if(input == null) {
                    amount = 20;
                } else {

                    if(!NumberUtils.isNumber(input)) {
                        message.updateMessage("`" + input + "` is not a valid cleanup amount!");
                        return;
                    }
                    amount = Integer.valueOf(input) + 1;
                }

                new MessageHistory(jda, e.getChannel()).retrieve(amount).parallelStream()
                        .filter(m -> m.getAuthor() == jda.getSelfInfo()).forEach(Message::deleteMessage);
                break;

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
                message.updateMessage("http://i.giphy.com/315b275sfehgs.gif");
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
                message.updateMessage("\u00af\\_(\u30c4)_/\u00af");
                break;

            case "lenny":
                message.updateMessage("( \u0361\u00b0 \u035c\u0296 \u0361\u00b0)");
                break;

            case "logger":
                if(input == null) {
                    Logger.toggle(!Logger.isEnabled());
                    message.updateMessage("`" + (Logger.isEnabled() ? "Enabled" : "Disabled") + " the logger`");
                } else {
                    switch(input.toLowerCase()) {
                        case "enable":
                            input = "true";
                        case "disable":
                            if(!input.equals("true"))
                                input = "false";
                        case "true":
                        case "false":
                            Logger.toggle(Boolean.valueOf(input));
                            message.updateMessage("`" + (Logger.isEnabled() ? "Enabled" : "Disabled") + " the logger`");
                            break;
                        case "status":
                            message.updateMessage("The logger is **" + (Logger.isEnabled() ? "enabled" : "disabled") + "**");
                            break;
                        default:
                            message.updateMessage("`" + input + "` is not a valid option!");
                            break;
                    }
                }
                break;
        }
    }
}