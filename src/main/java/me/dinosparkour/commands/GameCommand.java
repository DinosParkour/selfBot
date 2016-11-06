package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.CommandImpl;
import me.dinosparkour.utils.MessageUtil;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.Presence;

import java.util.Arrays;
import java.util.List;

public class GameCommand extends CommandImpl {

    @Override
    public void execute(String[] args, MessageReceivedEvent e, MessageSender chat) {
        String allArgs = String.join(" ", Arrays.asList(args));
        Presence presence = e.getJDA().getPresence();
        switch (allArgs) {
            case "":
                chat.update("Please provide a valid game name!");
                break;

            case "null":
            case "reset":
                presence.setGame(null);
                chat.update("Reset the game!");
                break;

            default:
                presence.setGame(Game.of(allArgs));
                String gameName = e.getJDA().getPresence().getGame().getName();
                chat.update("Set the game to \"" + MessageUtil.stripFormatting(gameName) + "\"");
                break;
        }
    }

    @Override
    public List<String> getAlias() {
        return Arrays.asList("game", "playing");
    }
}