package dev.wacho.proxy.command.chat;

import dev.wacho.proxy.BasicProxy;
import dev.wacho.proxy.util.ColorUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SendSoupCommand extends Command {

    private final BasicProxy instance;

    public SendSoupCommand(BasicProxy instance) {
        super("soup", "basic.command.soup");
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;

            if (instance.getProxy().getServers().containsKey("Soup")) {
                player.connect(instance.getProxy().getServerInfo("Soup"));
                player.sendMessage(ColorUtil.colorize("&aYou are being sent to the Soup server!"));
            } else {
                player.sendMessage(ColorUtil.colorize("&cThe Soup server is not available."));
            }
        } else {
            sender.sendMessage(ColorUtil.colorize("&cThis command can only be executed by players."));
        }
    }
}
