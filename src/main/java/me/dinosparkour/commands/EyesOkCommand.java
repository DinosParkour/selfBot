package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.ImgurCommandImpl;

import java.util.Collections;
import java.util.List;

public class EyesOkCommand extends ImgurCommandImpl {

    @Override
    public String getId() {
        return "BGViXAJ.gif";
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("eyesok");
    }
}