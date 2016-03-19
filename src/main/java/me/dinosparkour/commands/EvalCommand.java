package me.dinosparkour.commands;

import me.dinosparkour.main.BotInfo;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.concurrent.*;

/**
 * @author Dinos
 * @since 19/03/2016
 **/
public class EvalCommand extends ListenerAdapter {

    private ScriptEngine engine;

    public EvalCommand() {
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval("var imports = new JavaImporter(java.io, java.lang, java.util);");

        } catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        JDA jda = e.getJDA();
        TextChannel channel = e.getChannel();
        User author = e.getAuthor();
        Message message = e.getMessage();
        String msg = message.getContent();
        Guild guild = e.getGuild();

        String prefix = BotInfo.getPrefix();

        if(!author.getId().equals(BotInfo.getAuthorId())
                || !msg.startsWith(prefix + "eval")
                || !msg.contains(" ")) return;

        String input = msg.substring(msg.indexOf(' ')+1);

        engine.put("e", e);
        engine.put("jda", jda);
        engine.put("channel", channel);
        engine.put("author", author);
        engine.put("message", message);
        engine.put("guild", guild);
        engine.put("input", input);

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> future = service.schedule(() -> {

            Object out = null;
            try {
                out = engine.eval(
                        "(function() {" +
                                "with (imports) {\n" + input + "\n}" +
                                "})();");

            } catch (Exception ex) {
                channel.sendMessage("```\n" + ex.getCause().getMessage() + "```");
                return;
            }

            String inputS;
            String outputS;

            if(input.contains("\n"))
                inputS = "Input: ```\n" + input + "```";
            else
                inputS = "Input: `" + input + "`";

            if(out == null)
                outputS = "`Task executed without errors.`";
            else if(out.toString().contains("\n"))
                outputS = "Output: ```\n" + out.toString() + "```";
            else
                outputS = "Output: `" + out.toString() + "`";

            message.updateMessage(inputS + "\n\n" + outputS);

        }, 0, TimeUnit.MILLISECONDS);

        Thread script = new Thread("eval code"){
            @Override
            public void run() {
                try {
                    future.get(3750, TimeUnit.MILLISECONDS);

                } catch (TimeoutException  ex) {
                    future.cancel(true);
                    channel.sendMessage("Your task exceeds the time limit!");

                } catch (ExecutionException | InterruptedException  ex) {
                    String cause = ex.getMessage();
                    if(cause != null && channel.checkPermission(e.getJDA().getSelfInfo(), Permission.MESSAGE_WRITE))
                        channel.sendMessage("```" + cause + "```");
                }
            }
        };
        script.start();
    }
}