package dev.wacho.basic.commands.staff;

import dev.wacho.basic.Basic;
import dev.wacho.basic.BasicAPI;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.Utils;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnnounceCommand extends BaseCommand {

    public AnnounceCommand() {
        super("announce", null, "announceserver", "alertserver");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        Utils.globalBroadcast(player, CC.translate(Basic.getInst().getConfigFile().getString("SERVER-ANNOUNCE"))
                .replace("%name%", player.getName())
                .replace("%rank%", BasicAPI.getRankPrefix(player))
                .replace("%server%", Lang.SERVER_NAME));
        return true;
    }
}
