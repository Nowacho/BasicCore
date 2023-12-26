package dev.wacho.basic.commands.staff.coins;

import dev.wacho.basic.Basic;
import dev.wacho.basic.handler.menu.settings.SettingsGlobalMenu;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand extends BaseCommand {

    public CoinsCommand() {
        super("coins", null, "monedas");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

        if (args.length == 0) {
            sender.sendMessage(Lang.COINS.replace("%amount%", String.valueOf(playerData.getCoins())));
            return true;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if ((!target.hasPlayedBefore()) && (!target.isOnline())) {
            sender.sendMessage(Lang.OFFLINE_PLAYER);
            return false;
        }

        if (!sender.hasPermission("command.coins.player")) {
            sender.sendMessage(Lang.COINS_TARGET
                    .replace("%target%", target.getDisplayName())
                    .replace("%amount%", String.valueOf(playerData.getCoins())));
            return false;
        }
        return true;
    }
}
