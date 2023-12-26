package dev.wacho.basic.shop.tag;

import dev.wacho.basic.misc.tag.TagManager;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.shop.CoinShopMenu;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.button.BackButton;
import dev.wacho.basic.utils.menu.pagination.PaginatedMenu;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
public class TagShopMenu extends PaginatedMenu {

	private final PlayerData playerData;
	private final TagManager tagHandler;
	
	@Override
	public String getPrePaginatedTitle(Player player) {
		return "&9Tag Shop";
	}

	@Override
	public Map<Integer, Button> getAllPagesButtons(Player player) {
		Map<Integer, Button> buttons = new HashMap<Integer, Button>();
		
		getButtons().put(36, new BackButton(new CoinShopMenu(playerData)));
		
		tagHandler.getTagStore().stream().filter(tag -> tag.isPurchasable())
				.forEach(tag -> buttons.put(buttons.size(), new TagPurchasableButton(profile, tag)));
		
		return buttons;
	}
}
