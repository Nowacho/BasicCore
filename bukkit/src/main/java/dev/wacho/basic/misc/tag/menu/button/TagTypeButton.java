package org.mexican.habanero.tag.menu.button;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import org.mexican.habanero.Habanero;
import org.mexican.habanero.profile.Profile;
import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.tag.TagHandler;
import org.mexican.habanero.tag.TagType;
import org.mexican.habanero.tag.menu.TagTypeListMenu;
import org.mexican.habanero.util.ItemBuilder;
import org.mexican.habanero.util.TextUtil;
import org.mexican.habanero.util.menu.button.Button;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagTypeButton extends Button {

	private final TagType type;
	private final TagHandler tagHandler;
	
	@Override
	public ItemStack getButtonItem(Player player) {
		ItemBuilder builder = new ItemBuilder(Material.NETHER_STAR);
		
		builder.setName("&9" + TextUtil.getPrettyName(type.name()) + " Tags");
		
		builder.setLore(
				"",
				" &7Click here to view all",
				" &b" + type.name().toLowerCase() + " &7tags on the server",
				"",
				" &eYou currently own &r" + countOwnTags(player) + "/" + countTags() + "&b " + type.name().toLowerCase() + " &etags!",
				""
				);
		
		return builder.build();
	}

	public int countTags() {
		int result = 0;
		
		for (Tag tag : tagHandler.getTagStore()) {
			if (tag.getType().equals(type)) {
				result++;
			}
		}
		
		return result;
	}
	
	public int countOwnTags(Player player) {
		int result = 0;
		
		for (Tag tag : tagHandler.getTagStore()) {
			if (tag.getType().equals(type)) {
				Profile profile = Habanero.get().getProfileHandler().getProfile(player.getUniqueId());
				if (tag.requirePermission()) {
					if (profile.canSelectTag(tag)) {
						result++;
					}
				} else {
					result++;
				}
			}
		}
		
		return result;
	}
	
	@Override
	public void clicked(Player player, ClickType clickType) {
		if (clickType.isLeftClick()) {
			switch (type) {
			case NORMAL:
				new TagTypeListMenu(TagType.NORMAL, tagHandler).openMenu(player);
				break;
			case SPECIAL:
				new TagTypeListMenu(TagType.SPECIAL, tagHandler).openMenu(player);
				break;
			case COUNTRY:
				new TagTypeListMenu(TagType.COUNTRY, tagHandler).openMenu(player);
				break;
			case PARTNER:
				new TagTypeListMenu(TagType.PARTNER, tagHandler).openMenu(player);
				break;
			default:
				break;
			}
		}
	}
}
