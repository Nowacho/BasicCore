package dev.wacho.basic.handler.menu.settings;

import com.cryptomorin.xseries.XMaterial;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.TexturesUtil;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import dev.wacho.basic.utils.menu.button.CloseButton;
import lombok.AllArgsConstructor;
import me.activated.core.plugin.AquaCore;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.wacho.basic.utils.CC.translate;

public class SettingsGlobalMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8Global Settings ┃ 1/1");
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

        buttons.put(11, new SeparatorCategoryButton());
        buttons.put(0, new CloseButton());
        buttons.put(2, new GlobalSettingsCategoryButton());
        buttons.put(3, new ChatSettingsCategoryButton());

        buttons.put(19, new ToggleTablistButton());
        buttons.put(20, new ToggleScoreboardButton());
        buttons.put(21, new ToggleTimeButton());
        buttons.put(22, new ToggleAnnouncementsButton());

        setAutoUpdate(true);

        return buttons;
    }

    private static class SeparatorButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setName("&7⬆ Categories").setLore("&7⬇ Settings").setDurability((short) 7).toItemStack();
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

    //Category

    @AllArgsConstructor
    private static class GlobalSettingsCategoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial())
                    .setName("&aGlobal Settings")
                    .setLore("&eClick to view category settings.")
                    .setDurability((short) 3)
                    .setSkullOwner(TexturesUtil.EARTH_TEXTURE)
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }

    @AllArgsConstructor
    private static class ChatSettingsCategoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.SIGN)
                    .setName("&aChat Settings")
                    .setLore("&eClick to view category settings.").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            new SettingsChatMenu().openMenu(player);
        }
    }

    // Settings

    @AllArgsConstructor
    private class ToggleTablistButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.PAINTING)
                    .setName("&aTablist")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            PlayerData playerData = PlayerData.getPlayerData(player);

            List<String> lore = new ArrayList<>();
            lore.add("&7Change if you want to see the tablist.");
            lore.add("");
            /*if (playerData.getTabType() == TabType.WEIGHT) {
                lore.add("&eClick to deactivate the tablist.");
            } else {
                lore.add("&eClick to activate the tablist.");
            }*/
            return lore;
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            PlayerData playerData = PlayerData.getPlayerData(player);

            /*if (playerData.getTabType() == TabType.DEFAULT) playerData.setTabType(TabType.WEIGHT);
            else playerData.setTabType(TabType.DEFAULT);

            playerData.getPlayerOptions().vanillaTab(!playerData.getPlayerOptions().vanillaTab());

            if (playerData.getPlayerOptions().vanillaTab()) {
                player.sendMessage(CC.translate("&aYou have enabled vanilla tab!"));
            } else {
                player.sendMessage(CC.translate("&cYou have disabled vanilla tab!"));
            }*/

            //playerData.setTabEnabled(!playerData.isTabEnabled());
            update(player);
        }
    }

    @AllArgsConstructor
    private class ToggleScoreboardButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.ITEM_FRAME)
                    .setName("&aScoreboard")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            PlayerData playerData = PlayerData.getPlayerData(player);

            List<String> lore = new ArrayList<>();
            lore.add("&7Change if you want to see the scoreboard.");
            lore.add("");
            /*if (playerData.getPlayerOptions().showScoreboard()) {
                lore.add("&eClick to deactivate the scoreboard.");
            } else {
                lore.add("&eClick to activate the scoreboard.");
            }*/
            return lore;
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            PlayerData playerData = PlayerData.getPlayerData(player);
            //playerData.getPlayerOptions().showScoreboard(!playerData.getPlayerOptions().showScoreboard());
            update(player);
        }
    }

    @AllArgsConstructor
    private class ToggleTimeButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

            return new ItemBuilder(Material.WATCH)
                    //.setName(WordUtils.capitalize(playerData.getWorldTime().name()))
                    .setName("")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

            List<String> lore = new ArrayList<>();
            /*if (playerData.getWorldTime().equals(WorldTime.DAY)) {
                lore.add("&7Toggle whether you want the sky");
                lore.add("&7to appear nighttime for you.");
                lore.add("");
                lore.add("&eClick to enable night mode.");
            } else if (playerData.getWorldTime().equals(WorldTime.NIGHT)) {
                lore.add("&7Change if you want the sky");
                lore.add("&7to appear in the daytime for you.");
                lore.add("");
                lore.add("&eClick to enable day mode.");
            }*/
            return lore;
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());
            /*switch (playerData.getWorldTime()) {
                case DAY:
                    playerData.setWorldTime(WorldTime.DAY);
                    player.setPlayerTime(12500L, false);
                    break;
                case NIGHT:
                    playerData.setWorldTime(WorldTime.NIGHT);
                    player.resetPlayerTime();
                    break;
                default:
                    playerData.setWorldTime(WorldTime.DAY);
                    player.setPlayerTime(0L, false);
                    break;
            }*/
            update(player);
        }
    }

    @AllArgsConstructor
    private class ToggleAnnouncementsButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.NETHER_STAR)
                    .setName("&aAnnouncements")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            me.activated.core.api.player.PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            List<String> lore = new ArrayList<>();
            lore.add("&7Change if you want to see the ads.");
            lore.add("");
            if (aquaData.isTipsAlerts()) {
                lore.add("&eClick to deactivate the announcements.");
            } else {
                lore.add("&eClick to activate the announcements.");
            }
            return lore;
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            me.activated.core.api.player.PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
            aquaData.setTipsAlerts(!aquaData.isTipsAlerts());
            update(player);
        }
    }
}
