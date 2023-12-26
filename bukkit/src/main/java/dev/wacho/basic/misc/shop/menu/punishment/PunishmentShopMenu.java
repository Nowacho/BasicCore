package dev.wacho.basic.misc.shop.menu.punishment;

import dev.wacho.basic.misc.shop.menu.ranks.RankPermShopMenu;
import dev.wacho.basic.misc.shop.menu.ranks.RankTempShopMenu;
import dev.wacho.basic.misc.shop.menu.ranks.RankUpgradeShopMenu;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import lombok.AllArgsConstructor;
import me.activated.core.api.player.PlayerData;
import me.activated.core.managers.punishments.util.PunishmentUtil;
import me.activated.core.plugin.AquaCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

import static dev.wacho.basic.utils.CC.translate;

public class PunishmentShopMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8Punishment ┃ 1/1");
    }

    @Override
    public int size(Player player) {
        return 9 * 6;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        for (int i = 9; i <= 17; i++) {
            buttons.put(i, new SeparatorButton());
        }

        buttons.put(14, new SeparatorCategoryButton());
        buttons.put(0, new CloseButton());

        buttons.put(2, new LifetimeRankButton());
        buttons.put(3, new TemporalRankButton());
        buttons.put(4, new UpgradeRankButton());
        buttons.put(5, new PunishmentButton());
        buttons.put(6, new CosmeticButton());

        buttons.put(35, new BuyingButton());
        buttons.put(49, new CoinsButton());

        buttons.put(20, new WhitelistButton());
        buttons.put(21, new WhitelistChatButton());

        setAutoUpdate(true);

        return buttons;
    }

    private static class SeparatorButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setName("&7⬆ Categories").setDurability((short) 7).setLore("&7⬇ Items").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {

        }
    }

    private static class SeparatorCategoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setName("&aYou are viewing this category!").setDurability((short) 5).toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {

        }
    }

    @AllArgsConstructor
    private static class CloseButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.BED)
                    .setName("&cClose").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            player.closeInventory();
        }
    }

    /*private static class NextPageButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.ARROW)
                    .setName("&aNext Category").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            new SecondCategoryShopMenu().openMenu(player);
        }
    }*/

    @AllArgsConstructor
    private static class LifetimeRankButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.EMERALD)
                    .setName("&aLifetime Ranks")
                    .setLore("&7Enhance your experience with ranks",
                            "&7which offers you extra features!",
                            "&7Each rank contains all features from",
                            "&7previews one!",
                            "",
                            "&eClick to view category items.").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            new RankPermShopMenu().openMenu(player);
        }
    }

    @AllArgsConstructor
    private static class TemporalRankButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.EMERALD)
                    .setName("&aTemporal Ranks")
                    .setLore("&7Enhance your experience with ranks",
                            "&7which offers you extra features!",
                            "&7Each rank contains all features from",
                            "&7previews one!",
                            "",
                            "&7Purchased days will accumulate at any",
                            "&7time you decide to purchase this item again!",
                            "",
                            "&eClick to view category items.").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            new RankTempShopMenu().openMenu(player);
        }
    }

    @AllArgsConstructor
    private static class UpgradeRankButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.DIAMOND)
                    .setName("&aRank Upgrades")
                    .setLore("&7Enhance your experience with ranks",
                            "&7which offers you extra features!",
                            "&7Each rank contains all features from",
                            "&7previews one!",
                            "",
                            "&eClick to view category items.").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            new RankUpgradeShopMenu().openMenu(player);
        }
    }

    @AllArgsConstructor
    private static class PunishmentButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.IRON_FENCE)
                    .setName("&aPunishment")
                    .setLore("&7Get access again to chat",
                            "&7or play on the server!",
                            "",
                            "&aYou are viewing this category!").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }

    /*@AllArgsConstructor
    private static class VelnoxRankButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.NETHER_STAR)
                    .setName("&aVelnox+")
                    .setLore("&7Embrace the ultimate experience with",
                            "&7the &4Velnox&a+ &7rank which offers",
                            "&7all our exclusive features!",
                            "",
                            "&7Purchased days will accumulate at any",
                            "&7time you decide to purchase this item again!",
                            "",
                            "&eClick to view category items.").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            new VelnoxTempShopMenu().openMenu(player);
        }
    }*/

    @AllArgsConstructor
    private static class CosmeticButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.ENDER_CHEST)
                    .setName("&aCosmetics")
                    .setLore("&7Make you look unique by purchasing",
                            "&7a cosmetics between all the variety",
                            "&7we have available",
                            "",
                            "&eClick to view category items.").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }

    //Extra

    @AllArgsConstructor
    private static class BuyingButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
            skullMeta.setOwner(player.getName());
            playerHead.setItemMeta(skullMeta);

            return new ItemBuilder(playerHead)
                    .setName("&aBuying for: &b" + player.getName())
                    .setLore("&7You are buying for yourself!",
                            "",
                            "&cNote: &7If you are buying for another",
                            "&7player, both must be connected to",
                            "&7the same server.",
                            "",
                            "&eClick to buy for another player.").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {

        }
    }

    @AllArgsConstructor
    private static class CoinsButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
            return new ItemBuilder(Material.GOLD_NUGGET)
                    .setName("&6V-Coins: &f" + playerData.getCoins() + " &6⛁")
                    .setLore("&7V-Coins are used to acquire server",
                            "&7ranks, in-game cosmetics like chat",
                            "&7tags, suits, our particles!",
                            "",
                            "&7You can purchase V-Coins at",
                            "&fstore.velnox.club&7.").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {

        }
    }

    @AllArgsConstructor
    public static class WhitelistButton extends Button {

        public ItemStack getMaterial(Player player) {
            return new ItemStack(Material.IRON_FENCE, 1);
        }

        public String getName(Player player) {
            return CC.translate("&aWhitelist Access");
        }

        public List<String> getLore(Player player) {
            PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            List<String> list = new ArrayList<>();
            list.add(CC.translate("&8Whitelist Access"));
            list.add(CC.translate(""));
            list.add(CC.translate("&7Grants you whitelist access to play"));
            list.add(CC.translate("&7in Velnox Network!"));
            list.add(CC.translate(""));

            int coinsNeededForShadow = 1000;
            int playerCoins = playerData.getCoins();

            if (playerCoins >= coinsNeededForShadow) {
                list.add(CC.translate("&7Cost: &f1000 &6⛁"));
                list.add(CC.translate(""));
                list.add(CC.translate("&eClick to purchase the Whitelist."));
            } else {
                int coinsNeeded = coinsNeededForShadow - playerCoins;
                list.add(CC.translate("&7Cost: &f1000 &6⛁"));
                list.add(CC.translate(""));
                list.add(CC.translate("&cYou can't afford for this item."));
                list.add(CC.translate("&cYou need " + coinsNeeded + " more V-Coins."));
            }
            return list;
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            if (playerData.getCoins() < 1000) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                return;
            }

            if (playerData.getActiveBan() != null) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "unban " + player.getName() + " Bought");
                playerData.setCoins(playerData.getCoins() - 1000);
                playerData.save();
                player.sendMessage(CC.translate("&aSuccessfully bought Whitelist from Shop."));
            } else {
                player.sendMessage(CC.translate("&cYou are not currently banned."));
            }
        }

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(getMaterial(player)).setLore(getLore(player)).setName(getName(player)).toItemStack();
        }
    }

    @AllArgsConstructor
    public static class WhitelistChatButton extends Button {

        public ItemStack getMaterial(Player player) {
            return new ItemStack(Material.BOOK_AND_QUILL, 1);
        }

        public String getName(Player player) {
            return CC.translate("&aWhitelist Chat Access");
        }

        public List<String> getLore(Player player) {
            PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            List<String> list = new ArrayList<>();
            list.add(CC.translate("&8Whitelist Chat Access"));
            list.add(CC.translate(""));
            list.add(CC.translate("&7Grants you whitelist access to play"));
            list.add(CC.translate("&7in Velnox Network!"));
            list.add(CC.translate(""));

            int coinsNeededForShadow = 1000;
            int playerCoins = playerData.getCoins();

            if (playerCoins >= coinsNeededForShadow) {
                list.add(CC.translate("&7Cost: &f500 &6⛁"));
                list.add(CC.translate(""));
                list.add(CC.translate("&eClick to purchase the Whitelist Chat."));
            } else {
                int coinsNeeded = coinsNeededForShadow - playerCoins;
                list.add(CC.translate("&7Cost: &f500 &6⛁"));
                list.add(CC.translate(""));
                list.add(CC.translate("&cYou can't afford for this item."));
                list.add(CC.translate("&cYou need " + coinsNeeded + " more V-Coins."));
            }
            return list;
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            if (playerData.getCoins() < 500) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                return;
            }

            if (playerData.getActiveBan() != null) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "unmute " + player.getName() + " Bought");
                playerData.setCoins(playerData.getCoins() - 500);
                playerData.save();
                player.sendMessage(CC.translate("&aSuccessfully bought Whitelist Chat from Shop."));
            } else {
                player.sendMessage(CC.translate("&cYou are not currently muted."));
            }
        }

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(getMaterial(player)).setLore(getLore(player)).setName(getName(player)).toItemStack();
        }
    }
}
