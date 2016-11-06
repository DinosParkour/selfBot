package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.ImgurCommandImpl;

import java.util.Collections;
import java.util.List;

public class EyesCommand extends ImgurCommandImpl {

    @Override
    public String getId() {
        return "xpDT0p1.png";
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("eyes");
    }
}