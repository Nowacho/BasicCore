package dev.wacho.basic.commands.chat;

import dev.wacho.basic.Basic;
import dev.wacho.basic.BasicAPI;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand extends BaseCommand {

    private final Basic plugin = Basic.getInst();

    public HubCommand() {
        super("hub", null, "lobby", "l");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (BasicAPI.getLastServer(player) != null &&
                BasicAPI.getLastServer(player).equalsIgnoreCase(plugin.getConfigFile().getString("SERVER-LOBBY"))) {
            sender.sendMessage(Lang.SERVER_ALREADY_CONNECTED);
            return true;
        }

        BasicAPI.getToServer(player, plugin.getConfigFile().getString("SERVER-LOBBY"));
        sender.sendMessage(Lang.SERVER_CONNECTING.replace("%server%", "Lobby"));

        /*if (BasicAPI.getServerList().contains(plugin.getConfigFile().getString("SERVER-LOBBY"))) {
            BasicAPI.getToServer(player, plugin.getConfigFile().getString("SERVER-LOBBY"));

            sender.sendMessage(Lang.SERVER_CONNECTING.replace("%server%", "Lobby"));
        } else {
            sender.sendMessage(Lang.SERVER_NOT_FOUND);
        }*/
        return true;
    }
}
