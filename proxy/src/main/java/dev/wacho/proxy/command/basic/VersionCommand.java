package dev.wacho.proxy.command.basic;

import dev.wacho.proxy.BasicProxy;
import dev.wacho.proxy.util.ColorUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class VersionCommand extends Command {

    private final BasicProxy inst;

    public VersionCommand(BasicProxy inst) {
        super("bproxy", null, "basicproxy", "proxy");
        this.inst = inst;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        commandSender.sendMessage(ColorUtil.colorize("&8&m----------------------------------------"));
        commandSender.sendMessage(ColorUtil.colorize("&4&lBasicProxy"));
        commandSender.sendMessage(ColorUtil.colorize("&4▎ &fAuthor: &cNoWacho"));
        commandSender.sendMessage(ColorUtil.colorize("&4▎ &fDiscord: &cdiscord.velnox.club"));
        commandSender.sendMessage(ColorUtil.colorize(""));
        commandSender.sendMessage(ColorUtil.colorize("&4▎ &fVersion: &a1.0.3"));
        commandSender.sendMessage(ColorUtil.colorize("&4▎ &fLast Update &c07/1/23"));
        commandSender.sendMessage(ColorUtil.colorize("&8&m----------------------------------------"));
    }
}
