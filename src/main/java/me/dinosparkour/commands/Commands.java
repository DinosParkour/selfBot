package me.dinosparkour.commands;

import me.dinosparkour.main.BotInfo;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.MessageHistory;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashSet;

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

        if (!author.getId().equals(BotInfo.AUTHOR_ID)
                || !msg.startsWith(prefix)) return;

        String[] args = msg.split("\\s+");
        String command = args[0].substring(prefix.length());
        String input = null;
        if (msg.contains(" "))
            input = msg.substring(msg.indexOf(' ') + 1);

        switch (command.toLowerCase()) {
            case "apps":
                message.updateMessageAsync("<https://discordapp.com/developers/applications/me>", null);
                break;

            case "invite":
                message.updateMessageAsync("https://discordapp.com/oauth2/authorize?client_id=APP_ID&scope=bot"
                        + "\n\nReplace `APP_ID` in that link with your bot's Client/Application ID.", null);
                break;

            case "cancer":
                HashSet<String> cancers = new HashSet<>();
                e.getGuild().getUsers().stream()
                        .filter(u -> u.getUsername().replaceAll("[\\x20-\\x7E]*[\\x20-\\x7E]", "").length()
                                > (u.getUsername().length() / 2))
                        .map(u -> u.getUsername().replace("`", "\\`")
                                .replace("*", "\\*")
                                .replace("_", "\\_")
                                .replace("~~", "\\~\\~")
                                .replace("@everyone", "@\u180eeveryone")
                                .replace("@here", "@\u180ehere")
                                + "#" + u.getDiscriminator())
                        .forEach(cancers::add);

                String result = "__Names that will make your eyes bleed:__\n";
                if (cancers.size() > 35)
                    result += "**Too damn many!** - " + cancers.size() + " to be exact";
                else if (cancers.size() == 0)
                    result += "None! You're quite lucky to be in this guild.";
                else
                    result += String.join("\n", cancers);
                message.updateMessageAsync(result, null);
                break;

            case "cleanup":
                int amount;

                if (input == null) {
                    amount = 20;
                } else {
                    if (!NumberUtils.isNumber(input)) {
                        message.updateMessageAsync("`" + input + "` is not a valid cleanup amount!", null);
                        return;
                    } else if (input.length() >= 10) {
                        message.updateMessageAsync("Please use a lower cleanup amount!", null);
                        return;
                    }

                    amount = Integer.valueOf(input) + 1;
                }

                new MessageHistory(e.getChannel()).retrieve(amount).stream()
                        .filter(m -> m.getAuthor() == jda.getSelfInfo()).forEach(Message::deleteMessage);
                break;

            case "playing":
            case "game":
                if (input == null)
                    message.updateMessageAsync("Currently playing: `" + jda.getSelfInfo().getCurrentGame() + "`", null);
                else if (input.equalsIgnoreCase("null")) {
                    jda.getAccountManager().setGame("");
                    message.updateMessageAsync("Stopped playing.", null);
                } else {
                    jda.getAccountManager().setGame(input);
                    message.updateMessageAsync("Now playing: `" + jda.getSelfInfo().getCurrentGame() + "`", null);
                }
                break;

            case "meme":
            case "nicememe":
                message.updateMessageAsync("http://i.giphy.com/315b275sfehgs.gif", null);
                break;

            case "out":
            case "imout":
                message.updateMessageAsync("http://i.imgur.com/KBNcZ.gif", null);
                break;

            case "nope":
            case "ohgodwhy":
                message.updateMessageAsync("https://giphy.com/gifs/reaction-nope-oh-god-why-dqmpS64HsNvb2", null);
                break;

            case "prefix":
                if (input != null) {
                    BotInfo.setPrefix(input);
                    message.updateMessageAsync("New Prefix `" + input + "`", null);
                } else
                    message.updateMessageAsync("Current Prefix `" + prefix + "`", null);
                break;

            case "shrug":
                message.updateMessageAsync("\u00af\\_(\u30c4)_/\u00af", null);
                break;

            case "lenny":
                message.updateMessageAsync("( \u0361\u00b0 \u035c\u0296 \u0361\u00b0)", null);
                break;

            case "logger":
                if (input == null) {
                    Logger.toggle(!Logger.isEnabled());
                    message.updateMessageAsync("`" + (Logger.isEnabled() ? "Enabled" : "Disabled") + " the logger`", null);
                } else {
                    switch (input.toLowerCase()) {
                        case "enable":
                            input = "true";
                        case "disable":
                            if (!input.equals("true"))
                                input = "false";
                        case "true":
                        case "false":
                            Logger.toggle(Boolean.valueOf(input));
                            message.updateMessageAsync("`" + (Logger.isEnabled() ? "Enabled" : "Disabled") + " the logger`", null);
                            break;
                        case "status":
                            message.updateMessageAsync("The logger is **" + (Logger.isEnabled() ? "enabled" : "disabled") + "**", null);
                            break;
                        default:
                            message.updateMessageAsync("`" + input + "` is not a valid option!", null);
                            break;
                    }
                }
                break;
        }
    }
}