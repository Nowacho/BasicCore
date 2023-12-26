package dev.wacho.basic.misc.friend.commands;

import dev.wacho.basic.misc.friend.menu.ViewFriendsMenu;
import dev.wacho.basic.utils.command.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FriendCommand extends BaseCommand {

    public FriendCommand() {
        super("friends");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        new ViewFriendsMenu().openMenu(sender);
        return true;
    }
}
