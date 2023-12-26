package dev.wacho.basic.commands.chat;

import dev.wacho.basic.misc.cosmetics.tags.menus.NormalTagsMenu;
import dev.wacho.basic.utils.command.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagsCommand extends BaseCommand {

    public TagsCommand() {
        super("tag", "Open tags menu", "tags");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        new NormalTagsMenu().openMenu(player);
        return true;
    }
}
