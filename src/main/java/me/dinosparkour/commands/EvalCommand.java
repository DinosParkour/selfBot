package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.CommandImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;

public class EvalCommand extends CommandImpl {

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
    public void execute(String[] args, MessageReceivedEvent e, MessageSender chat) {
        String allArgs = String.join(" ", Arrays.asList(args));
        engine.put("e", e);
        engine.put("event", e);
        engine.put("api", e.getJDA());
        engine.put("jda", e.getJDA());
        engine.put("channel", e.getChannel());
        engine.put("author", e.getAuthor());
        engine.put("message", e.getMessage());
        engine.put("guild", e.getGuild());
        engine.put("input", allArgs);
        engine.put("mentionedUsers", e.getMessage().getMentionedUsers());
        engine.put("mentionedRoles", e.getMessage().getMentionedRoles());
        engine.put("mentionedChannels", e.getMessage().getMentionedChannels());

        String outputS = "Input: ```js\n" + allArgs.replace("`", "`\u180e") + "```\n";
        Object out;
        try {
            out = engine.eval("(function() { with (imports) {\n" + allArgs + "\n} })();");
        } catch (Exception ex) {
            chat.update(outputS + "**Exception**: ```\n" + ex.getLocalizedMessage() + "```");
            return;
        }

        if (out == null)
            outputS += "`Task executed without errors.`";
        else
            outputS += "Output: ```\n" + out.toString().replace("`", "`\u180e") + "\n```";

        chat.update(outputS);
    }

    @Override
    public List<String> getAlias() {
        return Arrays.asList("eval", "evaluate", "exec", "execute");
    }
}