package dev.wacho.basic.commands.staff.coins;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.JavaUtils;
import dev.wacho.basic.utils.NumberUtils;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsManagerCommand extends BaseCommand {

    public CoinsManagerCommand() {
        super("cm", null, "cmanager");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        /*if (args.length < 2) {
            sender.sendMessage(CC.translate("&8&m----------------------------------------"));
            sender.sendMessage(CC.translate("&c&lCoinsManager Help &7(Page 1/1)"));
            sender.sendMessage(CC.translate(""));
            sender.sendMessage(CC.translate("&f<> &7= &fRequired &7| &f[] &7= &fOptional"));
            sender.sendMessage(CC.translate(""));
            sender.sendMessage(CC.translate(" &f/coins set &7> Set world spawn."));
            sender.sendMessage(CC.translate(" &f/coins remove &7> Set practice spawn."));
            sender.sendMessage(CC.translate(""));
            sender.sendMessage(CC.translate("&7To view other pages, use '&c/Cm help <page#>&7'."));
            sender.sendMessage(CC.translate("&8&m----------------------------------------"));
            return false;
        }*/

        if (args.length < 2) {
            sender.sendMessage(CC.translate("&8&m----------------------------------------"));
            sender.sendMessage(CC.translate("&c&lCoinsManager Help &7(Page 1/1)"));
            sender.sendMessage(CC.translate(""));
            sender.sendMessage(CC.translate("&f<> &7= &fRequired &7| &f[] &7= &fOptional"));
            sender.sendMessage(CC.translate(""));
            sender.sendMessage(CC.translate(" &f/coins set &7> Set world spawn."));
            sender.sendMessage(CC.translate(" &f/coins remove &7> Set practice spawn."));
            sender.sendMessage(CC.translate(""));
            sender.sendMessage(CC.translate("&7To view other pages, use '&c/Cm help <page#>&7'."));
            sender.sendMessage(CC.translate("&8&m----------------------------------------"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[1]);
        if ((!target.hasPlayedBefore()) && (!target.isOnline())) {
            sender.sendMessage(Lang.OFFLINE_PLAYER);
            return false;
        }

        PlayerData targetData = PlayerData.getPlayerData(target.getUniqueId());
        if (targetData == null){
            player.sendMessage(Lang.OFFLINE_PLAYER);
            return false;
        }

        if (args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("add")) {
            if (args.length < 3) {
                sender.sendMessage(CC.translate("&cCorrect usage: /" + label + " give <player> <amount>."));
                return false;
            }

            Integer amount = JavaUtils.tryParseInt(args[2]);
            if (amount == null || amount <= 0) {
                sender.sendMessage(Lang.INVALID_NUMBER);
                return false;
            }

            targetData.setCoins(targetData.getCoins() + amount);

            sender.sendMessage(Lang.COINS_GIVE
                    .replace("%coins%", String.valueOf(targetData.getCoins()))
                    .replace("%amount%", String.valueOf(targetData.getCoins() + amount))
                    .replace("%target%", target.getDisplayName()));
            target.sendMessage(Lang.COINS_GIVE_TARGET
                    .replace("%coins%", String.valueOf(targetData.getCoins()))
                    .replace("%amount%", String.valueOf(targetData.getCoins() + amount))
                    .replace("%player%", sender.getName()));
            return true;
        } else if (args[0].equalsIgnoreCase("set")) {
            if (args.length < 3) {
                sender.sendMessage(CC.translate("&cCorrect usage: /" + label + " set <player> <amount>."));
                return false;
            }

            Integer amount = JavaUtils.tryParseInt(args[2]);
            if (amount == null || amount < 0) {
                sender.sendMessage(Lang.INVALID_NUMBER);
                return false;
            }

            targetData.setCoins(amount);

            sender.sendMessage(Lang.COINS_SET
                    .replace("%coins%", String.valueOf(targetData.getCoins()))
                    .replace("%amount%", String.valueOf(amount))
                    .replace("%target%", target.getDisplayName()));

            target.sendMessage(Lang.COINS_SET_TARGET
                    .replace("%coins%", String.valueOf(targetData.getCoins()))
                    .replace("%amount%", String.valueOf(amount))
                    .replace("%player%", player.getDisplayName()));
        } else if (args[0].equalsIgnoreCase("remove")) {
            if (args.length < 3) {
                sender.sendMessage(CC.translate("&cCorrect usage: /" + label + " remove <player> <amount>."));
                return false;
            }

            Integer amount = JavaUtils.tryParseInt(args[2]);
            if (amount == null || amount < 0) {
                sender.sendMessage(Lang.INVALID_NUMBER);
                return false;
            }

            targetData.setCoins(targetData.getCoins() - amount);

            target.sendMessage(Lang.COINS_REMOVED
                    .replace("%coins%", String.valueOf(targetData.getCoins()))
                    .replace("%amount%", String.valueOf(targetData.getCoins() - amount))
                    .replace("%player%", player.getDisplayName()));
        }
        return true;
    }
}
