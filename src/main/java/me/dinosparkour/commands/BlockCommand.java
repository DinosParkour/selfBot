package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.ImgurCommandImpl;

import java.util.Collections;
import java.util.List;

public class BlockCommand extends ImgurCommandImpl {

    @Override
    public String getId() {
        return "gHXwboc.png";
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("block");
    }
}