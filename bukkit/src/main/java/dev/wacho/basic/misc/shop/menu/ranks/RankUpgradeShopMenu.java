package dev.wacho.basic.misc.shop.menu.ranks;

import dev.wacho.basic.misc.shop.menu.ranks.impl.RankDecoyMenu;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
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
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

import static dev.wacho.basic.utils.CC.translate;

public class RankUpgradeShopMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8Rank Upgrades ┃ 1/1");
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

        buttons.put(13, new SeparatorCategoryButton());
        buttons.put(0, new CloseButton());

        buttons.put(2, new LifetimeRankButton());
        buttons.put(3, new TemporalRankButton());
        buttons.put(4, new UpgradeRankButton());
        buttons.put(5, new CosmeticButton());
        buttons.put(6, new TagsButton());

        buttons.put(27, new SearchItemButton());
        buttons.put(35, new BuyingButton());
        buttons.put(49, new CoinsButton());

        buttons.put(20, new DecoyUpgradeButton());
        buttons.put(21, new SouperUpgradeButton());
        buttons.put(22, new VelnoxUpgradeButton());

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
    public static class DecoyUpgradeButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                    .setRGBColor(173, 96, 182)
                    .setName("&8Shadow &d➠ Decoy Rank")
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

            int coinsNeededForShadow = 500;
            int playerCoins = playerData.getCoins();

            List<String> rankOrder = Arrays.asList("Decoy", "Souper", "Velnox", "Velnox+");

            int currentRankIndex = rankOrder.indexOf(rankData.getName());
            int targetRankIndex = rankOrder.indexOf("Decoy");

            if (currentRankIndex <= targetRankIndex) {
                if (rankData.getName().equalsIgnoreCase("Decoy")) {
                    list.add("&aAlready purchased!");
                } else {
                    if (playerCoins >= coinsNeededForShadow) {
                        list.add("&7Cost: &f500 &6⛁");
                        if (!rankData.getName().equalsIgnoreCase("Shadow")) {
                            list.add("");
                            list.add("&cRequires Shadow to purchase this.");
                        }
                        list.add("");
                        list.add("&eRight-Click to view all features.");
                    } else {
                        int coinsNeeded = coinsNeededForShadow - playerCoins;
                        list.add("&7Cost: &f500 &6⛁");
                        list.add("&cYou can't afford this item.");
                        list.add("&cYou need " + coinsNeeded + " more V-Coins.");
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

            List<String> rankOrder = Arrays.asList("Decoy", "Souper", "Velnox", "Velnox+");

            int currentRankIndex = rankOrder.indexOf(rankData.getName());
            int targetRankIndex = rankOrder.indexOf("Decoy");

            if (clickType == ClickType.LEFT) {
                if (currentRankIndex >= targetRankIndex) {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else if (playerData.getCoins() < 500) {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else {
                    if (rankData.getName().equalsIgnoreCase("Shadow")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setrank " + player.getName() + " Decoy perm global Bought");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                "alert " + playerData.getHighestRank().getPrefix() + player.getName() + " &fin our in-game store! If you want to support the server please consider visiting &c&nstore.velnox.club&f!");
                        playerData.setCoins(playerData.getCoins() - 500);
                        playerData.save();
                    } else {
                        player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                    }
                }
            } else if (clickType != ClickType.RIGHT) {
                player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
                new RankDecoyMenu().openMenu(player);
            }
        }
    }

    @AllArgsConstructor
    public static class SouperUpgradeButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                    .setRGBColor(87, 0, 87)
                    .setName("&dDecoy Rank &5➠ Souper Rank")
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

            int coinsNeededForShadow = 1000;
            int playerCoins = playerData.getCoins();

            List<String> rankOrder = Arrays.asList("Decoy", "Souper", "Velnox", "Velnox+");

            int currentRankIndex = rankOrder.indexOf(rankData.getName());
            int targetRankIndex = rankOrder.indexOf("Souper");

            if (currentRankIndex <= targetRankIndex) {
                if (rankData.getName().equalsIgnoreCase("Souper")) {
                    list.add("&aAlready purchased!");
                } else {
                    if (playerCoins >= coinsNeededForShadow) {
                        list.add("&7Cost: &f1000 &6⛁");
                        if (!rankData.getName().equalsIgnoreCase("Decoy")) {
                            list.add("");
                            list.add("&cRequires Decoy to purchase this.");
                        }
                        list.add("");
                        list.add("&eRight-Click to view all features.");
                    } else {
                        int coinsNeeded = coinsNeededForShadow - playerCoins;
                        list.add("&7Cost: &f1000 &6⛁");
                        list.add("&cYou can't afford this item.");
                        list.add("&cYou need " + coinsNeeded + " more V-Coins.");
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

            List<String> rankOrder = Arrays.asList("Decoy", "Souper", "Velnox", "Velnox+");

            int currentRankIndex = rankOrder.indexOf(rankData.getName());
            int targetRankIndex = rankOrder.indexOf("Souper");

            if (clickType == ClickType.LEFT) {
                if (currentRankIndex >= targetRankIndex) {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else if (playerData.getCoins() < 1000) {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else {
                    if (rankData.getName().equalsIgnoreCase("Decoy")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setrank " + player.getName() + " Souper perm global Bought");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                "alert " + playerData.getHighestRank().getPrefix() + player.getName() + " &fin our in-game store! If you want to support the server please consider visiting &c&nstore.velnox.club&f!");
                        playerData.setCoins(playerData.getCoins() - 1000);
                        playerData.save();
                    } else {
                        player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                    }
                }
            } else if (clickType != ClickType.RIGHT) {
                player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
                new RankDecoyMenu().openMenu(player);
            }
        }
    }

    @AllArgsConstructor
    public static class VelnoxUpgradeButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                    .setRGBColor(139, 0, 0)
                    .setName("&5Souper Rank &4➠ Velnox Rank")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            RankData rankData = AquaCoreAPI.INSTANCE.getPlayerRank(player.getUniqueId());
            PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            List<String> list = new ArrayList<>();
            list.add("&7Velnox provides standard features");
            list.add("&7for bypassing restrictions");
            list.add("");
            list.add("&7- Displayed as: &6[&c✡&4Velnox&6] &4" + player.getName());
            list.add("&7- &4Dark Red &7Name Color");
            list.add("&7- Chat delay restrictions bypass");
            list.add("&7- Access to &dVIP &7chat using &f/vip");
            list.add("&7- Join gamemodes directly by typing");
            list.add("&7their name, for example, &f/soup");
            list.add("&7- Hide your identity with &f/disguise&7!");
            list.add("&7- Access to all Normal chat tags");
            list.add("&7- Access to a single incoming");
            list.add("&7message on the server");
            list.add("&7and much more...");
            list.add("");

            int coinsNeededForShadow = 2000;
            int playerCoins = playerData.getCoins();

            List<String> rankOrder = Arrays.asList("Decoy", "Souper", "Velnox", "Velnox+");

            int currentRankIndex = rankOrder.indexOf(rankData.getName());
            int targetRankIndex = rankOrder.indexOf("Velnox");

            if (currentRankIndex <= targetRankIndex) {
                if (rankData.getName().equalsIgnoreCase("Velnox")) {
                    list.add("&aAlready purchased!");
                } else {
                    if (playerCoins >= coinsNeededForShadow) {
                        list.add("&7Cost: &f2000 &6⛁");
                        if (!rankData.getName().equalsIgnoreCase("Souper")) {
                            list.add("");
                            list.add("&cRequires Souper to purchase this.");
                        }
                        list.add("");
                        list.add("&eRight-Click to view all features.");
                    } else {
                        int coinsNeeded = coinsNeededForShadow - playerCoins;
                        list.add("&7Cost: &f2000 &6⛁");
                        list.add("&cYou can't afford this item.");
                        list.add("&cYou need " + coinsNeeded + " more V-Coins.");
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

            List<String> rankOrder = Arrays.asList("Decoy", "Souper", "Velnox", "Velnox+");

            int currentRankIndex = rankOrder.indexOf(rankData.getName());
            int targetRankIndex = rankOrder.indexOf("Velnox");

            if (clickType == ClickType.LEFT) {
                if (currentRankIndex >= targetRankIndex) {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else if (playerData.getCoins() < 2000) {
                    player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                } else {
                    if (rankData.getName().equalsIgnoreCase("Souper")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setrank " + player.getName() + " Velnox perm global Bought");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                                "alert " + playerData.getHighestRank().getPrefix() + player.getName() + " &fin our in-game store! If you want to support the server please consider visiting &c&nstore.velnox.club&f!");
                        playerData.setCoins(playerData.getCoins() - 2000);
                        playerData.save();
                    } else {
                        player.playSound(player.getLocation(), Sound.ANVIL_LAND, 40F, 30L);
                    }
                }
            } else if (clickType != ClickType.RIGHT) {
                player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
                new RankDecoyMenu().openMenu(player);
            }
        }
    }
}

