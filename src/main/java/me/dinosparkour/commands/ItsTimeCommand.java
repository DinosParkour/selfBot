package me.dinosparkour.commands;

import me.dinosparkour.commands.impls.ImgurCommandImpl;

import java.util.Arrays;
import java.util.List;

public class ItsTimeCommand extends ImgurCommandImpl {

    @Override
    public String getId() {
        return "ia3NQrv.png";
    }

    @Override
    public List<String> getAlias() {
        return Arrays.asList("itstime", "itstimetostop");
    }
}