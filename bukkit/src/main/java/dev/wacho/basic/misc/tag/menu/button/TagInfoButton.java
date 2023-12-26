package org.mexican.habanero.tag.menu.button;

import java.text.NumberFormat;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import org.mexican.habanero.Habanero;
import org.mexican.habanero.profile.Profile;
import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.tag.menu.TagEditorMenu;
import org.mexican.habanero.util.ColorUtil;
import org.mexican.habanero.util.ItemBuilder;
import org.mexican.habanero.util.TextUtil;
import org.mexican.habanero.util.UnicodeUtil;
import org.mexican.habanero.util.menu.button.Button;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagInfoButton extends Button {

	private final Tag tag;
	private final boolean editing, onlyInfo;
	
	@Override
	public ItemStack getButtonItem(Player player) {
		ItemBuilder builder = new ItemBuilder(Material.NAME_TAG);
		
		builder.setName("&9" + tag.getName());
		
		List<String> lore = Lists.newArrayList();
		
		lore.add("");
		lore.add(" &bThis looks&7:");
		lore.add(" &r" + tag.getDisplayName() + "&r " + player.getName());
		lore.add("");
		
		if (editing) {
			lore.add(" &bType&7: &r" + TextUtil.getPrettyName(tag.getType().name()));
			lore.add("");
			lore.add(" &bCost&7: &e" + UnicodeUtil.COIN + " &r" + NumberFormat.getInstance().format(tag.getCost()));
			lore.add(" &bPurchasable&7: &r" + (tag.isPurchasable() ? "&aYes" : "&cNo"));
			if (!onlyInfo) {
				lore.add("");
				lore.add(" &7» &eClick here to edit this tag &7«");
			}
		} else {
			Profile profile = Habanero.get().getProfileHandler().getProfile(player.getUniqueId());
			
			if (profile.canSelectTag(tag)) {
				lore.add(" &7» &eClick here to select this tag &7«");
			} else {
				if (tag.isPurchasable()) {
					lore.add(" &bCost&7: &r" + NumberFormat.getInstance().format(tag.getCost()));
					lore.add("");
				}
				lore.add(" &cYou don't own this tag!");
			}
		}
		
		lore.add("");
		
		builder.setLore(lore);
		
		return builder.build();
	}

	@Override
	public void clicked(Player player, ClickType clickType) {
		if (clickType.isLeftClick()) {
			if (editing && !onlyInfo) {
				new TagEditorMenu(tag).openMenu(player);
			} else {
				Profile profile = Habanero.get().getProfileHandler().getProfile(player.getUniqueId());
				
				if (profile.canSelectTag(tag)) {
					profile.setSelectedTagId(tag.getId());
					
					Habanero.get().getMongo().save(profile, "profiles");
					
					player.closeInventory();
					Button.playSuccess(player);
					
					player.sendMessage(ColorUtil.colorize("&aYou have selected '&f" + tag.getName() + "&a' tag!"));
				}
			}
		}
	}
}
