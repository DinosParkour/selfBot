package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.SimpleCommandImpl;

import java.util.Collections;
import java.util.List;

public class LennyCommand extends SimpleCommandImpl {

    @Override
    public String getMesasge() {
        return "( ͡° ͜ʖ ͡°)";
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("lenny");
    }
}