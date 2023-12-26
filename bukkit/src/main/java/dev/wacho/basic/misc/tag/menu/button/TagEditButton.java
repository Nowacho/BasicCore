package org.mexican.habanero.tag.menu.button;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.util.Callback;
import org.mexican.habanero.util.menu.button.Button;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagEditButton extends Button {

	private final Tag tag;
	private final ItemStack item;
	private final Callback<Tag> callback;
	
	@Override
	public ItemStack getButtonItem(Player player) {
		return item;
	}
	
	@Override
	public void clicked(Player player, ClickType clickType) {
		if (clickType.isLeftClick()) {
			callback.callback(tag);
		}
	}
}
