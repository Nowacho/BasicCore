package dev.wacho.proxy.command.chat;

import dev.wacho.proxy.BasicProxy;
import dev.wacho.proxy.util.ColorUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class TestHubCommand extends Command {

    private final BasicProxy instance;

    public TestHubCommand(BasicProxy instance) {
        super("hub", null, "lobby", "l");
        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;

            if (player.getServer().getInfo().getName().equalsIgnoreCase("Lobby")) {
                sender.sendMessage(ColorUtil.colorize("&cYou are already in the lobby server."));
                return;
            }

            instance.getProxy().getPlayer(player.getUniqueId()).connect(instance.getProxy().getServerInfo("Lobby"));
        } else {
            sender.sendMessage(ColorUtil.colorize("&cThis command can only be executed by players."));
        }
    }
}