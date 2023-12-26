package org.mexican.habanero.tag.menu.button;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import org.mexican.habanero.Habanero;
import org.mexican.habanero.profile.Profile;
import org.mexican.habanero.util.ItemBuilder;
import org.mexican.habanero.util.menu.button.Button;

public class TagResetButton extends Button {

	@Override
	public ItemStack getButtonItem(Player player) {
		Profile profile = Habanero.get().getProfileHandler().getProfile(player.getUniqueId());
		ItemBuilder builder = new ItemBuilder(Material.ANVIL);
		
		builder.setName("&cReset your tag");
		builder.setLore(
				"",
				"&7Current selected: &r" + (profile.getActiveTag() != null ? profile.getActiveTag().getName() : "None"),
				""
				);
		
		return builder.build();
	}

	@Override
	public void clicked(Player player, ClickType clickType) {
		if (clickType.isLeftClick()) {
			Profile profile = Habanero.get().getProfileHandler().getProfile(player.getUniqueId());
			profile.setSelectedTagId(null);
			
			Habanero.get().getMongo().save(profile, "profiles");
		}
	}
}
