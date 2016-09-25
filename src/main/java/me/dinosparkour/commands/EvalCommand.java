package me.dinosparkour.commands;

import me.dinosparkour.Info;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvalCommand extends ListenerAdapter {

    private final ScriptEngine engine;
    public EvalCommand() {
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval("var imports = new JavaImporter(java.io, java.lang, java.util);");
        } catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String msg = e.getMessage().getRawContent();
        if (!e.getAuthor().getId().equals(Info.AUTHOR_ID)
                || !msg.toLowerCase().startsWith(Info.PREFIX + "eval")
                || !msg.contains(" ")) return;
        String allArgs = msg.substring(msg.indexOf(' ')).trim();
        String inputS = "Input: ```js\n" + allArgs.replace("`", "\\`") + "```\n";

        engine.put("e", e);
        engine.put("event", e);
        engine.put("API", e.getJDA()); // .. because my phone autocorrects to all caps, :P
        engine.put("api", e.getJDA());
        engine.put("jda", e.getJDA());
        engine.put("channel", e.isPrivate() ? e.getPrivateChannel() : e.getTextChannel());
        engine.put("self", e.getAuthor());
        engine.put("author", e.getAuthor());
        engine.put("message", e.getMessage());
        engine.put("guild", e.getGuild());
        engine.put("input", allArgs);
        engine.put("mentionedUsers", e.getMessage().getMentionedUsers());
        engine.put("mentionedRoles", e.getMessage().getMentionedRoles());
        engine.put("mentionedChannels", e.getMessage().getMentionedChannels());

        Object out;
        try {
            out = engine.eval("(function() { with (imports) {\n" + allArgs + "\n} })();");
        } catch (Exception ex) {
            e.getMessage().editMessage(inputS + "**Exception**: ```\n" + ex.getLocalizedMessage() + "```").queue();
            return;
        }

        String outputS = inputS;
        if (out == null)
            outputS += "`Task executed without errors.`";
        else
            outputS += "Output: ```\n" + out.toString().replace("`", "\\`") + "```";

        if (outputS.length() >= 2000)
            outputS += "The output is too long!";

        e.getMessage().editMessage(outputS.replace("@", "@\u180e")).queue();
    }
}