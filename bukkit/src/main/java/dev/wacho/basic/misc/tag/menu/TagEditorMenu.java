package org.mexican.habanero.tag.menu;

import java.text.NumberFormat;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

import org.mexican.habanero.Habanero;
import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.tag.TagHandler;
import org.mexican.habanero.tag.menu.button.TagEditButton;
import org.mexican.habanero.tag.menu.button.TagInfoButton;
import org.mexican.habanero.tag.prompt.TagEditDurationPrompt;
import org.mexican.habanero.tag.prompt.TagEditIntegerPrompt;
import org.mexican.habanero.tag.prompt.TagEditStringPrompt;
import org.mexican.habanero.util.ColorUtil;
import org.mexican.habanero.util.ItemBuilder;
import org.mexican.habanero.util.TextUtil;
import org.mexican.habanero.util.TimeUtil;
import org.mexican.habanero.util.UnicodeUtil;
import org.mexican.habanero.util.menu.Menu;
import org.mexican.habanero.util.menu.button.Button;
import org.mexican.habanero.util.menu.button.impl.BackButton;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagEditorMenu extends Menu {

	private final Tag tag;
	
	@Override
	public String getTitle(Player player) {
		return "&9Editing &r" + tag.getName();
	}

	{
		setAutoUpdate(true);
	}
	
	@Override
	public Map<Integer, Button> getButtons(Player player) {
		Map<Integer, Button> buttons = Maps.newHashMap();
		
		for (int i = 0; i < 9; i++) buttons.put(i, getPlaceholderButton());
		
		TagHandler tagHandler = Habanero.get().getTagHandler();
		
		buttons.put(0, new BackButton(new TagViewAllMenu(tagHandler)));
		buttons.put(4, new TagInfoButton(tag, true, true));
		
		buttons.put(buttons.size(), new TagEditButton(
				tag,
				new ItemBuilder(Material.NAME_TAG)
					.setName("&bCurrent Name&7: &r" + tag.getName())
					.setLore(
							"",
							" &7» &eClick to rename &7«",
							""
							)
					.build(),
				(tag) -> {
					new TagEditStringPrompt(tag, (name) -> {
						if (tagHandler.getTag(name) != null) {
							player.sendMessage(ColorUtil.colorize("&cFailed to rename that tag because there are an existing tag with that name!"));
						} else {
							tag.setName(name);
							tagHandler.saveTag(tag);
							
							player.sendMessage(ColorUtil.colorize("&aYou have updated the '&r" + tag.getName() + "&a' tag name to '&r" + name + "&a'!"));
						}
						
					}).startPrompt(player);
					
					player.sendMessage(ColorUtil.colorize("&aWrite new name for '&r" + tag.getName() + "&a' tag..."));
				}
		));
		
		buttons.put(buttons.size(), new TagEditButton(
				tag,
				new ItemBuilder(Material.BOOK)
					.setName("&bCurrent Display&7: &r" + tag.getDisplayName() + " &r" + player.getName())
					.setLore(
							"",
							" &7» &eClick to edit display &7«",
							""
							)
					.build(),
				(tag) -> {
					new TagEditStringPrompt(tag, (display) -> {
						tag.setDisplayName(display);
						tagHandler.saveTag(tag);
						
						player.sendMessage(ColorUtil.colorize("&aYou have updated the '&r" + tag.getName() + "&a' tag display to '&r" + display + "&a'!"));
					}).startPrompt(player);
					
					player.sendMessage(ColorUtil.colorize("&aWrite new display for '&r" + tag.getName() + "&a' tag..."));
				}
		));
		
		buttons.put(buttons.size(), new TagEditButton(
				tag,
				new ItemBuilder(Material.ANVIL)
					.setName("&bCurrent Permission&7: &r" + tag.getPermission())
					.setLore(
							"",
							" &7» &eClick to edit permission &7«",
							""
							)
					.build(),
				(tag) -> {
					new TagEditStringPrompt(tag, (permission) -> {
						tag.setPermission(permission);
						tagHandler.saveTag(tag);
						
						player.sendMessage(ColorUtil.colorize("&aYou have updated the '&r" + tag.getName() + "&a' tag permission to '&r" + permission + "&a'!"));
					}).startPrompt(player);
					
					player.sendMessage(ColorUtil.colorize("&aWrite new permission for '&r" + tag.getName() + "&a' tag..."));
				}
		));
		
		buttons.put(buttons.size(), new TagEditButton(
				tag,
				new ItemBuilder(Material.DIAMOND)
					.setName("&bCurrent Type&7: &r" + TextUtil.getPrettyName(tag.getType().name()) + " ->&r " + TextUtil.getPrettyName(tag.getType().next().name()))
					.setLore(
							"",
							" &7» &eClick to change type &7«",
							""
							)
					.build(),
				(tag) -> {
					tag.setType(tag.getType().next());
				}
		));
		
		buttons.put(buttons.size() + 5, new TagEditButton(
				tag,
				new ItemBuilder(Material.INK_SACK)
				.setDurability(tag.isPurchasable() ? 10 : 1)
				.setName("&bPurchasable&7: &r" + (tag.isPurchasable() ? "&aYes" : "&cNo"))
				.setLore(
						"",
						" &7» &eClick to set" + (tag.isPurchasable() ? " not" : "") + " purchasable &7«",
						""
						)
				.build(),
				(tag) -> {
					tag.setPurchasable(!tag.isPurchasable());
					tagHandler.saveTag(tag);
				}
		));
		
		buttons.put(buttons.size() + 5, new TagEditButton(
				tag,
				new ItemBuilder(Material.WATCH)
					.setName("&bCurrent Purchase Duration&7: &r" + TimeUtil.readableTime(tag.getPurchaseDuration()))
					.setLore(
							"",
							" &7» &eClick to edit duration &7«",
							""
							)
					.build(),
				(tag) -> new TagEditDurationPrompt(tag, (duration) -> {
					tag.setPurchaseDuration(duration.getSeconds() * 1000L);
					tagHandler.saveTag(tag);
					
					player.sendMessage(ColorUtil.colorize("&aYou have updated the '&r" + tag.getName() + "&a' tag duration to '&r" + TimeUtil.readableTime(tag.getPurchaseDuration()) + "&a'!"));
				}).startPrompt(player)
		));
		
		buttons.put(buttons.size() + 5, new TagEditButton(
				tag,
				new ItemBuilder(Material.EMERALD)
					.setName("&bCurrent Coin Cost&7: &e" + UnicodeUtil.COIN + " &r" + NumberFormat.getInstance().format(tag.getCost()))
					.setLore(
							"",
							" &7» &eClick to edit coin cost &7«",
							""
							)
					.build(),
				(tag) -> {
					new TagEditIntegerPrompt(tag, (cost) -> {
						tag.setCost(cost);
						tagHandler.saveTag(tag);
						
						player.sendMessage(ColorUtil.colorize("&aYou have updated the '&r" + tag.getName() + "&a' tag coin cost to '&r" + NumberFormat.getInstance().format(cost) + "&a'!"));
					}).startPrompt(player);
					
					player.sendMessage(ColorUtil.colorize("&aWrite new coin cost for '&r" + tag.getName() + "&a' tag..."));
				}
		));
		
		return buttons;
	}

}
