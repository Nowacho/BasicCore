package dev.wacho.proxy.command.basic;

import dev.wacho.proxy.BasicProxy;
import dev.wacho.proxy.util.ColorUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {

    private final BasicProxy inst;

    public ReloadCommand(BasicProxy inst) {
        super("brl", "basic.command.reload");
        this.inst = inst;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission(getPermission())) {
            commandSender.sendMessage(ColorUtil.colorize("&cYou do not have permissions to use this command."));
            return;
        }
        commandSender.sendMessage(ColorUtil.colorize("&4&lBasicProxy &7configuration has been reloaded."));
        BasicProxy.getInst().reloadConfig();
    }
}
