package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.SimpleCommandImpl;

import java.util.Collections;
import java.util.List;

public class DocsCommand extends SimpleCommandImpl {

    @Override
    public String getMesasge() {
        return "<https://discordapp.com/developers/docs/intro>";
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("docs");
    }
}