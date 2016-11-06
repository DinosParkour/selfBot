package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.SimpleCommandImpl;

import java.util.Arrays;
import java.util.List;

public class ImOutCommand extends SimpleCommandImpl {

    @Override
    public String getMesasge() {
        return "https://i.imgur.com/KBNcZ.gif";
    }

    @Override
    public List<String> getAlias() {
        return Arrays.asList("imout", "out");
    }
}