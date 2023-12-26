package dev.wacho.proxy.command.chat;

import dev.wacho.proxy.BasicProxy;
import dev.wacho.proxy.util.ColorUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SendDevCommand extends Command {

    private final BasicProxy instance;

    public SendDevCommand(BasicProxy instance) {
        super("dev", "basic.command.dev");
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;

            if (instance.getProxy().getServers().containsKey("Dev")) {
                player.connect(instance.getProxy().getServerInfo("Dev"));
                player.sendMessage(ColorUtil.colorize("&aYou are being sent to the Dev server!"));
            } else {
                player.sendMessage(ColorUtil.colorize("&cThe Dev server is not available."));
            }
        } else {
            sender.sendMessage(ColorUtil.colorize("&cThis command can only be executed by players."));
        }
    }
}
