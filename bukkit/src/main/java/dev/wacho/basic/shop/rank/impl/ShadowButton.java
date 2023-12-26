package dev.wacho.basic.shop.rank.impl;

import dev.wacho.basic.misc.shop.menu.ranks.RankPermShopMenu;
import dev.wacho.basic.misc.shop.menu.ranks.impl.RankShadowMenu;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import lombok.AllArgsConstructor;
import me.activated.core.api.player.PlayerData;
import me.activated.core.api.rank.RankData;
import me.activated.core.plugin.AquaCore;
import me.activated.core.plugin.AquaCoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class ShadowButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                    .setRGBColor(80, 80, 80)
                    .setName("&8Shadow Rank")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            RankData rankData = AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId());
            PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            List<String> list = new ArrayList<>();
            list.add("&7Shadow provides standard features");
            list.add("&7for bypassing restrictions");
            list.add("");
            list.add("&7- Displayed as: &7[&f&l☽&8Shadow&7] &8" + player.getName());
            list.add("&7- &8Dark Gray &7Name Color");
            list.add("&7- Chat delay restrictions bypass");
            list.add("&7- Access to &dVIP &7chat using &f/vip");
            list.add("&7and much more...");
            list.add("");

            int coinsNeededForShadow = 700;
            int playerCoins = playerData.getCoins();

            List<String> rankOrder = Arrays.asList("Shadow", "Decoy", "Souper", "Velnox", "Velnox+");

            int currentRankIndex = rankOrder.indexOf(rankData.getName());
            int targetRankIndex = rankOrder.indexOf("Shadow");

            if (currentRankIndex <= targetRankIndex) {
                if (rankData.getName().equalsIgnoreCase("Shadow")) {
                    list.add("&aAlready purchased!");
                } else {
                    if (playerCoins >= coinsNeededForShadow) {
                        list.add("&7Cost: &f700 &6⛁");
                        list.add("");
                        list.add("&eRight-Click to view all features.");
                    } else {
                        int coinsNeeded = coinsNeededForShadow - playerCoins;
                        list.add("&7Cost: &f700 &6⛁");
                        list.add("");
                        list.add("&cYou can't afford this item.");
                        list.add("&cYou need " + coinsNeeded + " more V-Coins.");
                        list.add("");
                        list.add("&eRight-Click to view all features.");
                    }
                }
            } else {
                list.add("&aAlready purchased!");
            }
            return list;
        }

    @Override
    public void clicked(Player player, ClickType clickType) {
        RankData rankData = AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId());
        PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

        List<String> rankOrder = Arrays.asList("Shadow", "Decoy", "Souper", "Velnox", "Velnox+");

        int currentRankIndex = rankOrder.indexOf(rankData.getName());
        int targetRankIndex = rankOrder.indexOf("Shadow");

        if (clickType == ClickType.LEFT) {
            if (currentRankIndex >= targetRankIndex) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
            } else if (playerData.getCoins() < 700) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
            } else {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setrank " + player.getName() + " Shadow perm global Bought");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "alert " + playerData.getHighestRank().getPrefix() + player.getName() +
                        " &fin our in-game store! If you want to support the server please consider visiting &c&nstore.velnox.club&f!");
                playerData.setCoins(playerData.getCoins() - 700);
                playerData.save();
                new RankPermShopMenu().openMenu(player);
            }
        } else if (clickType == ClickType.RIGHT) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            new RankShadowMenu().openMenu(player);
        }
    }
}
