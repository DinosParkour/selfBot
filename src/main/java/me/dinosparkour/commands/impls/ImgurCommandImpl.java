package me.dinosparkour.commands.impls;

public abstract class ImgurCommandImpl extends SimpleCommandImpl {

    protected abstract String getId();

    @Override
    public String getMesasge() {
        return "https://i.imgur.com/" + getId();
    }
}