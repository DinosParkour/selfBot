package me.dinosparkour.commands;

import me.dinosparkour.Info;
import me.dinosparkour.commands.impls.SimpleCommandImpl;

import java.util.Collections;
import java.util.List;

public class UptimeCommand extends SimpleCommandImpl {

    @Override
    public String getMesasge() {
        return "**Uptime:** `" + Info.getUptime() + "`";
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("uptime");
    }
}