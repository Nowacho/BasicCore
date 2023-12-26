package org.mexican.habanero.tag.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.mexican.habanero.Habanero;
import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.tag.TagHandler;
import org.mexican.habanero.tag.menu.TagEditorMenu;
import org.mexican.habanero.tag.menu.TagListMenu;
import org.mexican.habanero.tag.menu.TagViewAllMenu;
import org.mexican.habanero.util.ColorUtil;
import org.mexican.habanero.util.command.annotation.Command;
import org.mexican.habanero.util.command.annotation.Param;

public class TagCommands {

	@Command(names = "tags")
	public static void tags(Player player) {
		new TagListMenu(Habanero.get().getTagHandler()).openMenu(player);
	}
	
	@Command(names = "tagsadmin create", permission = "helium.command.tags.create")
	public static void create(CommandSender sender, @Param(name = "tag name") String tagName) {
		TagHandler tagHandler = Habanero.get().getTagHandler();
		
		Tag tag = tagHandler.getTag(tagName);
		if (tag != null) {
			sender.sendMessage(ColorUtil.colorize("&cThat tag is already created!"));
			return;
		}
		
		tagHandler.createTag(tagName);
		sender.sendMessage(ColorUtil.colorize("&aYou have created the '&f" + tagName + "&a' tag!"));
	}
	
	@Command(names = "tagsadmin delete", permission = "helium.command.tags.delete")
	public static void delete(CommandSender sender, @Param(name = "tag name") Tag tag) {
		TagHandler tagHandler = Habanero.get().getTagHandler();
		
		sender.sendMessage(ColorUtil.colorize("&cYou have deleted the '&f"+ tag.getName() + "&c' tag!"));
		tagHandler.deleteTag(tag);
	}
	
	@Command(names = "tagsadmin list", permission = "helium.command.tags.list")
	public static void list(Player player) {
		new TagViewAllMenu(Habanero.get().getTagHandler()).openMenu(player);
	}
	
	@Command(names = "tagsadmin edit", permission = "helium.command.tags.edit")
	public static void edit(Player player, @Param(name = "tag name") Tag tag) {
		new TagEditorMenu(tag).openMenu(player);
	}
}
