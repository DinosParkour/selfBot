package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.ImgurCommandImpl;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CringeCommand extends ImgurCommandImpl {

    @Override
    public String getId() {
        return (new Random().nextDouble() > 0.5 ? "mM5wGEP.gif" : "ETaqR4U.gif");
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("cringe");
    }
}