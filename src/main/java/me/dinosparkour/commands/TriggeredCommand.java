package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.ImgurCommandImpl;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TriggeredCommand extends ImgurCommandImpl {

    @Override
    public String getId() {
        return (new Random().nextDouble() > 0.5 ? "Yug8HWJ.gif" : "XNgfclR.gif");
    }

    @Override
    public List<String> getAlias() {
        return Collections.singletonList("triggered");
    }
}