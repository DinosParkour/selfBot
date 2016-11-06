package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.SimpleCommandImpl;

import java.util.Collections;
import java.util.List;

public class AppsCommand extends SimpleCommandImpl {

    @Override
    public String getMesasge() {
        return "<https://discordapp.com/developers/applications/me>";
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("apps");
    }
}