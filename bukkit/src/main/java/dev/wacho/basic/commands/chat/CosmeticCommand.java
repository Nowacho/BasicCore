package dev.wacho.basic.commands.chat;

import dev.wacho.basic.misc.cosmetics.effects.menu.EffectsMenu;
import dev.wacho.basic.utils.command.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CosmeticCommand extends BaseCommand {

    public CosmeticCommand() {
        super("cosmetic", "Open cosmetics menu", "menu/cosmetics");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        new EffectsMenu().openMenu(player);
        return true;
    }
}
