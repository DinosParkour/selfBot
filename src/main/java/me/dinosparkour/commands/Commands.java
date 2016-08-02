package me.dinosparkour.commands;

import me.dinosparkour.main.BotInfo;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.MessageHistory;
import net.dv8tion.jda.entities.Game;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashSet;
import java.util.Random;

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

            case "docs":
                message.updateMessageAsync("<https://discordapp.com/developers/docs/intro>", null);
                break;

            case "invite":
                message.updateMessageAsync("https://discordapp.com/oauth2/authorize?client_id=APP_ID&scope=bot"
                        + "\n\nReplace `APP_ID` in that link with your bot's Client/Application ID.", null);
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
                if (input == null) {
                    Game game = jda.getSelfInfo().getCurrentGame();
                    message.updateMessageAsync("Currently playing: `" + (game != null ? game.getName() : null) + "`", null);
                } else if (input.equalsIgnoreCase("null") || input.equalsIgnoreCase("reset")) {
                    jda.getAccountManager().setGame("");
                    message.updateMessageAsync("Stopped playing.", null);
                } else {
                    jda.getAccountManager().setGame(input);
                    message.updateMessageAsync("Now playing: `" + jda.getSelfInfo().getCurrentGame().getName() + "`", null);
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

            case "ratelimits":
                message.updateMessageAsync("http://i.imgur.com/P6bDtR9.gif", null);
                break;

            case "itstime":
            case "itstimetostop":
                message.updateMessageAsync("http://i.imgur.com/ia3NQrv.png", null);
                break;

            case "eyes":
                message.updateMessageAsync("http://i.imgur.com/xpDT0p1.png", null);
                break;

            case "eyesok":
                message.updateMessageAsync("http://i.imgur.com/BGViXAJ.gif", null);
                break;

            case "block":
                message.updateMessageAsync("http://i.imgur.com/gHXwboc.png", null);
                break;

            case "leave":
                message.updateMessageAsync("http://i.imgur.com/taMA1xX.png", null);
                break;

            case "triggered":
                message.updateMessageAsync((new Random().nextDouble() > 0.5
                        ? "http://i.imgur.com/Yug8HWJ.gif" : "http://i.imgur.com/XNgfclR.gif"), null);
                break;

            case "cringe":
                message.updateMessageAsync((new Random().nextDouble() > 0.5
                        ? "http://i.imgur.com/mM5wGEP.gif" : "http://i.imgur.com/ETaqR4U.gif"), null);
                break;

            case "prefix":
                if (input != null) {
                    BotInfo.setPrefix(input);
                    message.updateMessageAsync("New Prefix `" + input + "`", null);
                } else
                    message.updateMessageAsync("Current Prefix `" + prefix + "`", null);
                break;

            case "lenny":
                message.updateMessageAsync("( \u0361\u00b0 \u035c\u0296 \u0361\u00b0)", null);
                break;
        }
    }
}
