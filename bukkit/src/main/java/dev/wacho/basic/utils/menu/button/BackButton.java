package dev.wacho.basic.utils.menu.button;

import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class BackButton extends Button {

	private final Menu currentMenu;

	public BackButton(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

	@Override
	public ItemStack getButtonItem(Player player) {
		return new ItemBuilder(Material.BED)
				.setName("&cGo Back").toItemStack();
	}

	@Override
	public void clicked(Player player, ClickType clickType) {
		player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
		Button.playNeutral(player);
		this.currentMenu.openMenu(player);
	}
}
