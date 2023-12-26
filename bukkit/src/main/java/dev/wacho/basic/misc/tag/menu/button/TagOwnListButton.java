package org.mexican.habanero.tag.menu.button;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import org.mexican.habanero.Habanero;
import org.mexican.habanero.profile.Profile;
import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.tag.TagHandler;
import org.mexican.habanero.tag.menu.TagOwnListMenu;
import org.mexican.habanero.util.ItemBuilder;
import org.mexican.habanero.util.menu.button.Button;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagOwnListButton extends Button {

	private final TagHandler tagHandler;
	
	@Override
	public ItemStack getButtonItem(Player player) {
		ItemBuilder builder = new ItemBuilder(Material.ENDER_CHEST);
		
		builder.setName("&9Your own tags");
		
		builder.setLore(
				"",
				" &7Click to show all your",
				" &7own tags on the server",
				"",
				" &eYou currently own &r" + countOwnTags(player) + "/" + tagHandler.getTagStore().size() + " &etags!",
				""
				);
		
		return builder.build();
	}
	
	public int countOwnTags(Player player) {
		int result = 0;
		
		for (Tag tag : tagHandler.getTagStore()) {
			Profile profile = Habanero.get().getProfileHandler().getProfile(player.getUniqueId());
			if (tag.requirePermission()) {
				if (profile.canSelectTag(tag)) {
					result++;
				}
			} else {
				result++;
			}
		}
		
		return result;
	}
	
	@Override
	public void clicked(Player player, ClickType clickType) {
		if (clickType.isLeftClick()) {
			new TagOwnListMenu(tagHandler).openMenu(player);
		}
	}
}
