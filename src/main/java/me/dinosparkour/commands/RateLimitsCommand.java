package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.ImgurCommandImpl;

import java.util.Collections;
import java.util.List;

public class RateLimitsCommand extends ImgurCommandImpl {

    @Override
    public String getId() {
        return "P6bDtR9.gif";
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("ratelimits");
    }
}