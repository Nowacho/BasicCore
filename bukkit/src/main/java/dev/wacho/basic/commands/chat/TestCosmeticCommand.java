package dev.wacho.basic.commands.chat;

import dev.wacho.basic.misc.cosmetics.joinmessage.menu.JoinMessageMenu;
import dev.wacho.basic.utils.command.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCosmeticCommand extends BaseCommand {

    public TestCosmeticCommand() {
        super("test");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        new JoinMessageMenu().openMenu(player);
        return true;
    }
}
