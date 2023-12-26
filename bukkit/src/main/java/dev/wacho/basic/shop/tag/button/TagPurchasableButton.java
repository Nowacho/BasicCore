package dev.wacho.basic.shop.tag.button;

import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import org.mexican.habanero.Habanero;
import org.mexican.habanero.profile.Profile;
import org.mexican.habanero.profile.grant.type.TagGrant;
import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.util.ColorUtil;
import org.mexican.habanero.util.ItemBuilder;
import org.mexican.habanero.util.TimeUtil;
import org.mexican.habanero.util.UnicodeUtil;
import org.mexican.habanero.util.menu.button.Button;

public class TagPurchasableButton extends Button {

	private Profile profile;
	private Tag tag;
	
	public TagPurchasableButton(Profile profile, Tag tag) {
		this.profile = profile;
		this.tag = tag;
	}
	
	@Override
	public ItemStack getButtonItem(Player player) {
		ItemBuilder builder = new ItemBuilder(Material.NAME_TAG);
		
		builder.setName("&9" + tag.getName());
		NumberFormat format = NumberFormat.getInstance();
		
		List<String> lore = Lists.newArrayList();
		lore.add(" ");
		lore.add(" &4Note: &cWhen buying this");
		lore.add(" &crank there will be no refunds");
		lore.add(" &cor exchanges and it is not");
		lore.add(" &cstackable.");
		lore.add(" ");
		lore.add(" &bThis rank expires in");
		lore.add(" &f" + TimeUtil.readableTime(tag.getPurchaseDuration()));
		lore.add(" &bafter purchase.");
		lore.add(" ");
		lore.add(" &bCost&7: &e" + UnicodeUtil.COIN + "&r " + format.format(tag.getCost()) + "&7 (You have " + format.format(profile.getCoins()) + ")");
		lore.add(" ");
		if(tag.getCost() > profile.getCoins()) {
			lore.add(" &7» &cYou don't have enough coins to buy this tag &7«");
		} else {
			lore.add(" &7» &eClick here to buy this tag &7«");
		}
		
		builder.setLore(lore);
		
		return builder.build();
	}
	
	@Override
	public void clicked(Player player, ClickType clickType) {
		if(clickType.isLeftClick()) {
			if(tag.getCost() > profile.getCoins()) {
				player.sendMessage(ColorUtil.colorize("&cYou don't have enough coins to buy this tag."));
				return;
			}
			
			profile.setCoins(profile.getCoins() - tag.getCost());
			
			TagGrant grant = new TagGrant(UUID.randomUUID(), tag.getId());
			grant.setAddedAt(System.currentTimeMillis());
			grant.setAddedReason("Purchased tag with Coins.");
			grant.setDuration(TimeUtil.parseTime("7d"));
			
			profile.addGrant(grant);
			
			Habanero.get().getMongo().save(profile, "profiles");
			
			player.closeInventory();
			player.sendMessage(ColorUtil.colorize("&aYour purchase was completed successfully."));
		}
	}
}