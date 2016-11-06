package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.ImgurCommandImpl;

import java.util.Arrays;
import java.util.List;

public class GodWhyCommand extends ImgurCommandImpl {

    @Override
    public String getId() {
        return "QfULNaf.gif";
    }

    @Override
    public List<String> getAlias() {
        return Arrays.asList("godwhy", "ohgodwhy", "nope");
    }
}