package me.dinosparkour.commands;

import me.dinosparkour.Info;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Random;

public class Commands extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        Message message = e.getMessage();
        String msg = message.getContent();

        if (!e.getAuthor().getId().equals(Info.AUTHOR_ID)
                || !msg.startsWith(Info.PREFIX)) return;

        String command = msg.substring(Info.PREFIX.length());
        if (msg.contains(" "))
            command = command.substring(command.indexOf(' '));
        command = command.toLowerCase();
        String input = msg.substring(command.length()).trim();

        switch (command.toLowerCase()) {
            case "apps":
                message.editMessage("<https://discordapp.com/developers/applications/me>").queue();
                break;

            case "docs":
                message.editMessage("<https://discordapp.com/developers/docs/intro>").queue();
                break;

            case "invite":
                message.editMessage("https://discordapp.com/oauth2/authorize?client_id=APP_ID&scope=bot"
                        + "\n\nReplace `APP_ID` in that link with your bot's Client/Application ID.").queue();
                break;

            case "cleanup":
                int amount;
                try {
                    amount = input.isEmpty() ? 20 : Integer.valueOf(input) + 1;
                    if (amount <= 1) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    message.editMessage("*That's not a valid amount!").queue();
                    return;
                }

                e.getChannel().getHistory().retrieve(amount).stream()
                        .filter(m -> m.getAuthor().equals(e.getAuthor()))
                        .forEach(Message::deleteMessage);
                break;

            /* TODO: Work on this once the implementation is actually present
            case "playing":
            case "game":
                Game game = e.getMember().getGame(); // This throws a NPE if it's a private message
                if (input.isEmpty()) {
                    message.editMessage("Currently playing: `" + game.getName() + "`").queue();
                } else if (input.equalsIgnoreCase("null") || input.equalsIgnoreCase("reset")) {
                    jda.getAccountManager().setGame("");
                    message.editMessage("Stopped playing.").queue();
                } else {
                    jda.getAccountManager().setGame(input);
                    message.editMessage("Now playing: `" + game.getName() + "`").queue();
                }
                break;
            */

            case "meme":
            case "nicememe":
                message.editMessage("http://i.giphy.com/315b275sfehgs.gif").queue();
                break;

            case "out":
            case "imout":
                message.editMessage("http://i.imgur.com/KBNcZ.gif").queue();
                break;

            case "nope":
            case "ohgodwhy":
                message.editMessage("https://giphy.com/gifs/reaction-nope-oh-god-why-dqmpS64HsNvb2").queue();
                break;

            case "ratelimits":
                message.editMessage("http://i.imgur.com/P6bDtR9.gif").queue();
                break;

            case "itstime":
            case "itstimetostop":
                message.editMessage("http://i.imgur.com/ia3NQrv.png").queue();
                break;

            case "eyes":
                message.editMessage("http://i.imgur.com/xpDT0p1.png").queue();
                break;

            case "eyesok":
                message.editMessage("http://i.imgur.com/BGViXAJ.gif").queue();
                break;

            case "block":
                message.editMessage("http://i.imgur.com/gHXwboc.png").queue();
                break;

            case "leave":
                message.editMessage("https://i.imgur.com/24kgkMx.png").queue();
                break;

            case "triggered":
                message.editMessage((new Random().nextDouble() > 0.5
                        ? "http://i.imgur.com/Yug8HWJ.gif" : "http://i.imgur.com/XNgfclR.gif")).queue();
                break;

            case "cringe":
                message.editMessage((new Random().nextDouble() > 0.5
                        ? "http://i.imgur.com/mM5wGEP.gif" : "http://i.imgur.com/ETaqR4U.gif")).queue();
                break;

            case "lenny":
                message.editMessage("( ͡° ͜ʖ ͡°)").queue();
                break;
        }
    }
}