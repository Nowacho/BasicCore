package dev.wacho.basic.shop.rank.button;

import com.google.common.collect.Lists;
import dev.wacho.basic.Basic;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.TimeUtil;
import dev.wacho.basic.utils.menu.Button;
import me.activated.core.api.rank.RankData;
import me.activated.core.api.rank.grant.Grant;
import me.activated.core.enums.AMaterial;
import me.activated.core.enums.Language;
import me.activated.core.plugin.AquaCore;
import me.activated.core.utilities.general.DateUtils;
import me.activated.core.utilities.general.Tasks;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;

public class RankPurchasableButton extends Button {

	private PlayerData playerData;
	private RankData rankData;
	
	public RankPurchasableButton(PlayerData playerData, RankData rankData) {
		this.playerData = playerData;
		this.rankData = rankData;
	}

	/*@Override
	public ItemStack getButtonItem(Player player) {
		me.activated.core.api.player.PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

		ItemBuilder item = new ItemBuilder(AMaterial.retrieveWoolFromData(playerData.hasRank(rankData) ? 5 : rankData.getCoinsCost() > playerData.getCoins() ? 8 : 10));
		item.setName(rankData.getDisplayName());
		if (playerData.hasRank(rankData)) {
			item.addLoreLine(" ");
			item.addLoreLine("&cYou already own this");
			item.addLoreLine("&crank and can't purchase it!");
			item.addLoreLine(" ");
		} else if (rankData.getCoinsCost() > playerData.getCoins()) {
			item.addLoreLine(" ");
			item.addLoreLine("&cYou don't have enough coins");
			item.addLoreLine("&cto afford purchase of &c&l" + rankData.getName() + "&c!");
			item.addLoreLine(" ");
			item.addLoreLine("&7Keep playing to get coins");
			item.addLoreLine("&7You still need &b" + (rankData.getCoinsCost() - playerData.getCoins()) + " coins &7to");
			item.addLoreLine("&7to purchase this rank");
			item.addLoreLine(" ");
			item.addLoreLine("&c&lNote&7: &c&lAll ranks are temporary");
			item.addLoreLine("&cand will not last forever!");
			item.addLoreLine(" ");
		} else {
			item.addLoreLine(" ");
			item.addLoreLine("&7You have enough coins to");
			item.addLoreLine("&7afford this rank.");
			item.addLoreLine(" ");
			item.addLoreLine("&7Once you purchase this you will stay");
			item.addLoreLine("&7with &b" + (playerData.getCoins() - rankData.getCoinsCost()) + " coins &7in your account!");
			item.addLoreLine(" ");
			item.addLoreLine("&bYour Coins&7: &3" + playerData.getCoins());
			item.addLoreLine("&bPrice&7: &a$" + rankData.getCoinsCost());
			item.addLoreLine("&bCurrent rank&7: " + playerData.getHighestRank().getDisplayName());
			item.addLoreLine(" ");
			item.addLoreLine("&c&lNote&7: &c&lAll ranks are temporary");
			item.addLoreLine("&cand will not last forever!");
			item.addLoreLine(" ");
			if (rankData.getWeight() < playerData.getHighestRank().getWeight()) {
				item.addLoreLine("&9This rank has lower priority");
				item.addLoreLine("&9than your current rank and will");
				item.addLoreLine("&9not display as your main rank!");
				item.addLoreLine(" ");
			}
		}
		return item.toItemStack();
	}*/

	@Override
	public ItemStack getButtonItem(Player player) {
		ItemBuilder builder = new ItemBuilder(Material.WOOL);
		builder.setName(rankData.getDisplayName());
		NumberFormat format = NumberFormat.getInstance();

		List<String> lore = Lists.newArrayList();
		lore.add(" ");
		lore.add(" &4Note: &cWhen buying this");
		lore.add(" &crank there will be no refunds");
		lore.add(" &cor exchanges and it is not");
		lore.add(" &cstackable.");
		lore.add(" ");
		lore.add(" &bThis rank expires in");
		//lore.add(" &f" + TimeUtil.readableTime(aquaData.getPurchasableRanks().size()));
		//lore.add(" &f" + TimeUtil.readableTime(playerData.getPurchaseDuration()));
		lore.add(" &bafter purchase.");
		lore.add(" ");
		lore.add(" &bCost&7: &e " + format.format(rankData.getCoinsCost()) + "&7 (You have " + format.format(playerData.getCoins()) + ")");
		lore.add(" ");
		if(rankData.getCoinsCost() > playerData.getCoins()) {
			lore.add(" &7» &cYou don't have enough coins to buy this rank  &7«");
		} else {
			lore.add(" &7» &eClick here to buy this rank &7«");
		}

		builder.setLore(lore);
		builder.setDurability(CC.getWoolColorFromChatColor(rankData.getColor()));

		return builder.toItemStack();
	}

	/*@Override
	public void clicked(Player player, ClickType clickType) {
		me.activated.core.api.player.PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

		if (playerData.hasRank(rankData)) return;
		if (rankData.getCoinsCost() > playerData.getCoins()) return;

		Grant grant = new Grant();
		grant.setAddedAt(System.currentTimeMillis());
		grant.setRankName(rankData.getName());
		grant.setPermanent(false);

		long duration = DateUtils.parseDateDiff(AquaCore.INSTANCE.getSettings().getString("purchasable-ranks-duration", "30d"), false);

		if (duration == -5L) {
			duration = System.currentTimeMillis() - DateUtils.parseDateDiff("30d", false);
		} else {
			duration = System.currentTimeMillis() - duration;
		}

		grant.setDuration(duration);
		grant.setAddedBy("Console");
		grant.setReason("Purchased By Coins");
		grant.setActive(true);

		playerData.setCoins(playerData.getCoins() - rankData.getCoinsCost());
		playerData.getGrants().add(grant);

		player.sendMessage(Language.COINS_RANK_PURCHASED.toString()
				.replace("<rank>", rankData.getDisplayName())
				.replace("<coins>", String.valueOf(playerData.getCoins())));

		Tasks.runAsync(AquaCore.INSTANCE, playerData::save);
	}*/

	@Override
	public void clicked(Player player, ClickType clickType) {
		if(clickType.isLeftClick()) {
			if(rankData.getCoinsCost() > playerData.getCoins()) {
				player.sendMessage(CC.translate("&cYou don't have enough coins to buy this rank."));
				return;
			}
			
			playerData.setCoins(playerData.getCoins() - rankData.getCoinsCost());

			//Grant grant = new Grant(UUID.randomUUID(), rankData.getId());
			Grant grant = new Grant();
			grant.setAddedAt(System.currentTimeMillis());
			grant.setReason("Purchased rank with Coins");
			grant.setDuration(TimeUtil.parseTime("7d"));
			grant.setActive(true);

			grant.setRankName(rankData.getName());
			grant.setPermanent(false);

			grant.setAddedBy("Console");

			Basic.getInst().getMongoManager().getMongoDatabase().getCollection("PurchasedRank");
			
			player.closeInventory();
			player.sendMessage(CC.translate("&aYour purchase was completed successfully."));
		}
	}
}