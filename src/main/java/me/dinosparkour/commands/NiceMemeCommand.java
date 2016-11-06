package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.ImgurCommandImpl;

import java.util.Arrays;
import java.util.List;

public class NiceMemeCommand extends ImgurCommandImpl {

    @Override
    public String getId() {
        return "7FWJZbn.gif";
    }

    @Override
    public List<String> getAlias() {
        return Arrays.asList("meme", "nicememe");
    }
}