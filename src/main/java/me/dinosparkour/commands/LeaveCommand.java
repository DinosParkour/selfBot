package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.ImgurCommandImpl;

import java.util.Collections;
import java.util.List;

public class LeaveCommand extends ImgurCommandImpl {

    @Override
    public String getId() {
        return "24kgkMx.png";
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("leave");
    }
}