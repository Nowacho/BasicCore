package dev.wacho.basic.misc.shop;

import dev.wacho.basic.misc.shop.menu.ranks.RankPermShopMenu;
import dev.wacho.basic.utils.command.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand extends BaseCommand {

    public ShopCommand() {
        super("store", null, "buy");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        new RankPermShopMenu().openMenu(player);
        return true;
    }
}
