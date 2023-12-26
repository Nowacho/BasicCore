package dev.wacho.basic.misc.shop.menu.ranks;

import dev.wacho.basic.misc.shop.menu.punishment.PunishmentShopMenu;
import dev.wacho.basic.misc.shop.menu.ranks.impl.RankDecoyMenu;
import dev.wacho.basic.misc.shop.menu.ranks.impl.RankShadowMenu;
import dev.wacho.basic.misc.shop.menu.ranks.impl.RankSouperMenu;
import dev.wacho.basic.misc.shop.menu.ranks.impl.RankVelnoxMenu;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import lombok.AllArgsConstructor;
import me.activated.core.api.player.PlayerData;
import me.activated.core.api.rank.RankData;
import me.activated.core.plugin.AquaCore;
import me.activated.core.plugin.AquaCoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

import static dev.wacho.basic.utils.CC.translate;

public class RankTempShopMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8Temporal Ranks ┃ 1/1");
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

        buttons.put(12, new SeparatorCategoryButton());
        buttons.put(0, new CloseButton());

        buttons.put(2, new LifetimeRankButton());
        buttons.put(3, new TemporalRankButton());
        buttons.put(4, new UpgradeRankButton());
        buttons.put(5, new CosmeticButton());
        buttons.put(6, new TagsButton());

        buttons.put(27, new SearchItemButton());
        buttons.put(35, new BuyingButton());
        buttons.put(49, new CoinsButton());

        buttons.put(20, new ShadowButton());
        buttons.put(21, new DecoyButton());
        buttons.put(22, new SouperButton());

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
                            "&aYou are viewing this category!").toItemStack();
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

    @AllArgsConstructor
    private static class TagsButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.NAME_TAG)
                    .setName("&aChat Tags")
                    .setLore("&7Purchase a cool chat tag to show",
                            "&7off in the public chat.",
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
                            "&cFunction temporarily disabled!").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {

        }
    }

    @AllArgsConstructor
    private static class SearchItemButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.COMPASS)
                    .setName("&aSearch Item")
                    .setLore("&7Find items easily by typing their name.",
                            "",
                            "&cFunction temporarily disabled!").toItemStack();
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

    //Ranks purchased

    @AllArgsConstructor
    public static class ShadowButton extends Button {

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

            int coinsNeededForShadow = 300;
            int playerCoins = playerData.getCoins();

            List<String> rankOrder = Arrays.asList("Shadow", "Decoy", "Souper", "Velnox", "Velnox+");

            int currentRankIndex = rankOrder.indexOf(rankData.getName());
            int targetRankIndex = rankOrder.indexOf("Shadow");

            if (currentRankIndex <= targetRankIndex) {
                if (rankData.getName().equalsIgnoreCase("Shadow")) {
                    list.add("&aAlready purchased!");
                } else {
                    if (playerCoins >= coinsNeededForShadow) {
                        list.add("&7Cost: &f300 &6⛁");
                        list.add("");
                        list.add("&eRight-Click to view all features.");
                    } else {
                        int coinsNeeded = coinsNeededForShadow - playerCoins;
                        list.add("&7Cost: &f300 &6⛁");
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
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else if (playerData.getCoins() < 300) {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setrank " + player.getName() + " Shadow 30d global Bought");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "alert " + playerData.getHighestRank().getPrefix() + player.getName() +
                            " &fin our in-game store! If you want to support the server please consider visiting &c&nstore.velnox.club&f!");
                    playerData.setCoins(playerData.getCoins() - 300);
                    playerData.save();
                    new RankPermShopMenu().openMenu(player);
                }
            } else if (clickType == ClickType.RIGHT) {
                player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
                new RankShadowMenu().openMenu(player);
            }
        }
    }

    @AllArgsConstructor
    public static class DecoyButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                    .setRGBColor(173, 96, 182)
                    .setName("&dDecoy Rank")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            RankData rankData = AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId());
            PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            List<String> list = new ArrayList<>();
            list.add("&7Decoy provides standard features");
            list.add("&7for bypassing restrictions");
            list.add("");
            list.add("&7- Displayed as: &7[&f✩&dDecoy&7] &d" + player.getName());
            list.add("&7- &dLight Purple &7Name Color");
            list.add("&7- Chat delay restrictions bypass");
            list.add("&7- Access to &dVIP &7chat using &f/vip");
            list.add("&7- Join gamemodes directly by typing");
            list.add("&7their name, for example, &f/soup");
            list.add("&7and much more...");
            list.add("");

            int coinsNeededForShadow = 800;
            int playerCoins = playerData.getCoins();

            List<String> rankOrder = Arrays.asList("Shadow", "Decoy", "Souper", "Velnox", "Velnox+");

            int currentRankIndex = rankOrder.indexOf(rankData.getName());
            int targetRankIndex = rankOrder.indexOf("Decoy");

            if (currentRankIndex <= targetRankIndex) {
                if (rankData.getName().equalsIgnoreCase("Decoy")) {
                    list.add("&aAlready purchased!");
                } else {
                    if (playerCoins >= coinsNeededForShadow) {
                        list.add("&7Cost: &f800 &6⛁");
                        list.add("");
                        list.add("&eRight-Click to view all features.");
                    } else {
                        int coinsNeeded = coinsNeededForShadow - playerCoins;
                        list.add("&7Cost: &f800 &6⛁");
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
            int targetRankIndex = rankOrder.indexOf("Decoy");

            if (clickType == ClickType.LEFT) {
                if (currentRankIndex >= targetRankIndex) {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else if (playerData.getCoins() < 800) {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setrank " + player.getName() + " Decoy 30d global Bought");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "alert " + playerData.getHighestRank().getPrefix() + player.getName() +
                            " &fin our in-game store! If you want to support the server please consider visiting &c&nstore.velnox.club&f!");
                    playerData.setCoins(playerData.getCoins() - 800);
                    playerData.save();
                    new RankPermShopMenu().openMenu(player);
                }
            } else if (clickType == ClickType.RIGHT) {
                player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
                new RankDecoyMenu().openMenu(player);
            }
        }
    }

    @AllArgsConstructor
    public static class SouperButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                    .setRGBColor(87, 0, 87)
                    .setName("&5Souper Rank")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            RankData rankData = AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId());
            PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            List<String> list = new ArrayList<>();
            list.add("&7Souper provides standard features");
            list.add("&7for bypassing restrictions");
            list.add("");
            list.add("&7- Displayed as: &7[&d✫&5Souper&7] &5" + player.getName());
            list.add("&7- &5Dark Purple &7Name Color");
            list.add("&7- Chat delay restrictions bypass");
            list.add("&7- Access to &dVIP &7chat using &f/vip");
            list.add("&7- Join gamemodes directly by typing");
            list.add("&7their name, for example, &f/soup");
            list.add("&7- Hide your identity with &f/disguise&7!");
            list.add("&7and much more...");
            list.add("");

            int coinsNeededForShadow = 1200;
            int playerCoins = playerData.getCoins();

            List<String> rankOrder = Arrays.asList("Shadow", "Decoy", "Souper", "Velnox", "Velnox+");

            int currentRankIndex = rankOrder.indexOf(rankData.getName());
            int targetRankIndex = rankOrder.indexOf("Souper");

            if (currentRankIndex <= targetRankIndex) {
                if (rankData.getName().equalsIgnoreCase("Souper")) {
                    list.add("&aAlready purchased!");
                } else {
                    if (playerCoins >= coinsNeededForShadow) {
                        list.add("&7Cost: &f1200 &6⛁");
                        list.add("");
                        list.add("&eRight-Click to view all features.");
                    } else {
                        int coinsNeeded = coinsNeededForShadow - playerCoins;
                        list.add("&7Cost: &f1200 &6⛁");
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
            int targetRankIndex = rankOrder.indexOf("Souper");

            if (clickType == ClickType.LEFT) {
                if (currentRankIndex >= targetRankIndex) {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else if (playerData.getCoins() < 1200) {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setrank " + player.getName() + " Souper 30d global Bought");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "alert " + playerData.getHighestRank().getPrefix() + player.getName() +
                            " &fin our in-game store! If you want to support the server please consider visiting &c&nstore.velnox.club&f!");
                    playerData.setCoins(playerData.getCoins() - 1200);
                    playerData.save();
                    new RankPermShopMenu().openMenu(player);
                }
            } else if (clickType == ClickType.RIGHT) {
                player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
                new RankSouperMenu().openMenu(player);
            }
        }
    }
}
