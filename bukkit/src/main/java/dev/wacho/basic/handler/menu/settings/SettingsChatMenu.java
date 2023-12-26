package dev.wacho.basic.handler.menu.settings;

import com.cryptomorin.xseries.XMaterial;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import dev.wacho.basic.utils.menu.button.CloseButton;
import lombok.AllArgsConstructor;
import me.activated.core.api.player.PlayerData;
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

public class SettingsChatMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8Chat Settings ┃ 1/1");
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
        buttons.put(2, new GlobalSettingsCategoryButton());
        buttons.put(3, new ChatSettingsCategoryButton());

        buttons.put(19, new ToggleGlobalChatButton());
        buttons.put(20, new ToggleChatMentionButton());
        buttons.put(21, new ToggleMessagesButton());
        buttons.put(22, new ToggleMessagesSoundButton());

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
            return new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short) 3)
                    .setName("&aGlobal Settings")
                    .setLore("&eClick to view category settings.")
                    .setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc5ZTU0Y2JlODc4NjdkMTRiMmZiZGYzZjE4NzA4OTQzNTIwNDhkZmVjZDk2Mjg0NmRlYTg5M2IyMTU0Yzg1In19fQ==")
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            new SettingsGlobalMenu().openMenu(player);
        }
    }

    @AllArgsConstructor
    private static class ChatSettingsCategoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.SIGN)
                    .setName("&aChat Settings")
                    .setLore("&aYou are viewing this category!").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }

    // Settings

    @AllArgsConstructor
    private class ToggleGlobalChatButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.BOOK)
                    .setName("&aGlobal Chat")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            List<String> lore = new ArrayList<>();
            lore.add("&7Choose whether you want to see");
            lore.add("&7global chat messages or not.");
            lore.add(" ");
            if (aquaData.getMessageSystem().isGlobalChat()) {
                lore.add("&eClick to deactivate the global messages.");
            } else {
                lore.add("&eClick to activate the global messages.");
            }
            return lore;
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
            aquaData.getMessageSystem().setGlobalChat(!aquaData.getMessageSystem().isGlobalChat());
            update(player);
        }
    }

    @AllArgsConstructor
    private class ToggleChatMentionButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.NETHER_STAR)
                    .setName("&aChat Mention")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            List<String> lore = new ArrayList<>();
            lore.add("&7Choose whether you want to be alerted");
            lore.add("&7when someone mentions your name or not.");
            lore.add(" ");
            if (aquaData.getMessageSystem().isChatMention()) {
                lore.add("&eClick to deactivate chat mentions.");
            } else {
                lore.add("&eClick to activate chat mentions.");
            }
            return lore;
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
            aquaData.getMessageSystem().setChatMention(!aquaData.getMessageSystem().isChatMention());
            update(player);
        }
    }

    @AllArgsConstructor
    private class ToggleMessagesButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.BOOK_AND_QUILL)
                    .setName("&aPrivate Messages")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            List<String> lore = new ArrayList<>();
            lore.add("&7Choose whether you want to");
            lore.add("&7receive private messages or not.");
            lore.add("");
            if (aquaData.getMessageSystem().isMessagesToggled()) {
                lore.add("&eClick to deactivate private messages.");
            } else {
                lore.add("&eClick to activate private messages.");
            }
            return lore;
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
            aquaData.getMessageSystem().setMessagesToggled(!aquaData.getMessageSystem().isMessagesToggled());
            update(player);
        }
    }

    @AllArgsConstructor
    private class ToggleMessagesSoundButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.NOTE_BLOCK)
                    .setName("&aPrivate Messages Sounds")
                    .setLore(getsetLore(player)).toItemStack();
        }

        public List<String> getsetLore(Player player) {
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

            List<String> lore = new ArrayList<>();
            lore.add("&7Choose whether you want to receive");
            lore.add("&7private messages sound or not.");
            lore.add("");
            if (aquaData.getMessageSystem().isSoundsEnabled()) {
                lore.add("&eClick to deactivate messages sounds.");
            } else {
                lore.add("&eClick to activate messages sounds.");
            }
            return lore;
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
            aquaData.getMessageSystem().setSoundsEnabled(!aquaData.getMessageSystem().isSoundsEnabled());
            update(player);
        }
    }
}
