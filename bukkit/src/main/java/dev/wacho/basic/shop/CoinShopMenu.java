package dev.wacho.basic.shop;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.shop.rank.RankShopMenu;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import me.activated.core.plugin.AquaCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CoinShopMenu extends Menu {

	private final PlayerData playerData;
	
	@Override
	public String getTitle(Player player) {
		return "&9Coins Shop";
	}

	@Override
	public Map<Integer, Button> getButtons(Player player) {
		Map<Integer, Button> buttons = new HashMap<Integer, Button>();
		
		buttons.put(3, new Button() {
			
			@Override
			public ItemStack getButtonItem(Player player) {
				return new ItemBuilder(Material.WOOL).setDurability(1)
						.setName("&3Rank Shop")
						.setLore("", " &7» &eClick to open rank shop menu &7«", "").toItemStack();
			}
			
			@Override
			public void clicked(Player player, ClickType clickType) {
				if(clickType.isLeftClick()) {
					new RankShopMenu(playerData, AquaCore.INSTANCE.getRankManagement().getRank(player.getName())).openMenu(player);
					Button.playSuccess(player);
				}
			}
		});
		
		buttons.put(5, new Button() {
			
			@Override
			public ItemStack getButtonItem(Player player) {
				return new ItemBuilder(Material.NAME_TAG)
						.setName("&eTag Shop")
						.setLore("", " &7» &eClick to open tag shop menu &7«", "").toItemStack();
			}
			
			@Override
			public void clicked(Player player, ClickType clickType) {
				if(clickType.isLeftClick()) {
					new TagShopMenu(profile, Habanero.get().getTagHandler()).openMenu(player);
					Button.playSuccess(player);
				}
			}
		});
		
		return buttons;
	}
}
