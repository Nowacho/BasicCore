package dev.wacho.basic.commands.chat;

import dev.wacho.basic.handler.menu.settings.SettingsGlobalMenu;
import dev.wacho.basic.utils.command.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SettingsCommand extends BaseCommand {

    public SettingsCommand() {
        super("settings", "Open settings menu");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        new SettingsGlobalMenu().openMenu(player);
        return true;
    }
}
