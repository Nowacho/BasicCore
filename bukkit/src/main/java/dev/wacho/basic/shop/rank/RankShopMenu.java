package dev.wacho.basic.shop.rank;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.shop.CoinShopMenu;
import dev.wacho.basic.shop.rank.button.RankPurchasableButton;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.button.BackButton;
import dev.wacho.basic.utils.menu.pagination.PaginatedMenu;
import me.activated.core.api.rank.RankData;
import org.bukkit.entity.Player;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class RankShopMenu extends PaginatedMenu {

	private final PlayerData playerData;
	private final RankData rankData;
	
	@Override
	public String getPrePaginatedTitle(Player player) {
		return "&9Rank Shop";
	}

	@Override
	public Map<Integer, Button> getAllPagesButtons(Player player) {
		Map<Integer, Button> buttons = new HashMap<Integer, Button>();
		
		getButtons().put(36, new BackButton(new CoinShopMenu(playerData)));


		rankData.getRanks().stream().filter(rank -> rank.isPurchasable()).filter(rank -> !rank.isDefaultRank())
				.forEach(rank -> buttons.put(buttons.size(), new RankPurchasableButton(playerData, rankData)));
		
		return buttons;
	}
}
