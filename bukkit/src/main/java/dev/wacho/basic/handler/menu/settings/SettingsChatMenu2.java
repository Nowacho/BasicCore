package dev.wacho.basic.handler.menu.settings;

import dev.wacho.basic.Basic;
import dev.wacho.basic.misc.cosmetics.utils.all.SeparatorButton;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import me.activated.core.api.player.PlayerData;
import me.activated.core.plugin.AquaCore;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class SettingsChatMenu2 extends Menu {

    @Override
    public String getTitle(Player player) {
        return Basic.getInst().getConfig().getString("SETTINGS-CHAT.TITLE");
    }

    @Override
    public int size(Player player) {
        return Basic.getInst().getConfig().getInt("SETTINGS-CHAT.SIZE") * 9;

    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        for (int i = 9; i <= 17; i++) {
            buttons.put(i, new SeparatorButton());
        }

        if (Basic.getInst().getConfigFile().getBoolean("SETTINGS-CHAT.GLOBAL-CHAT.ENABLED"))
            buttons.put(Basic.getInst().getConfigFile().getInt("SETTINGS-CHAT.GLOBAL-CHAT.SLOT"), new ToggleGlobalChatButton());

        return buttons;
    }

    private static class ToggleGlobalChatButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            if (AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId()).getMessageSystem().isGlobalChat()) {
                return new ItemBuilder(Material.valueOf(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.ITEM")))
                        .setDurability((short) Basic.getInst().getConfigFile().getInt("SETTINGS-CHAT.GLOBAL-CHAT.DATA"))
                        .setName(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.DISPLAY-NAME"))
                        .setLore(Basic.getInst().getConfigFile().getStringList("SETTINGS-CHAT.GLOBAL-CHAT.LORE-ENABLE")).toItemStack();
            } else {
                return new ItemBuilder(Material.valueOf(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.ITEM")))
                        .setDurability((short) Basic.getInst().getConfigFile().getInt("SETTINGS-CHAT.GLOBAL-CHAT.DATA"))
                        .setName(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.DISPLAY-NAME"))
                        .setLore(Basic.getInst().getConfigFile().getStringList("SETTINGS-CHAT.GLOBAL-CHAT.LORE-DISABLE")).toItemStack();
            }
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
            aquaData.getMessageSystem().setGlobalChat(!aquaData.getMessageSystem().isGlobalChat());
        }
    }

    private static class ToggleChatMentionButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            if (AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId()).getMessageSystem().isChatMention()) {
                return new ItemBuilder(Material.valueOf(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.ITEM")))
                        .setDurability((short) Basic.getInst().getConfigFile().getInt("SETTINGS-CHAT.GLOBAL-CHAT.DATA"))
                        .setName(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.DISPLAY-NAME"))
                        .setLore(Basic.getInst().getConfigFile().getStringList("SETTINGS-CHAT.GLOBAL-CHAT.LORE-ENABLE")).toItemStack();
            } else {
                return new ItemBuilder(Material.valueOf(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.ITEM")))
                        .setDurability((short) Basic.getInst().getConfigFile().getInt("SETTINGS-CHAT.GLOBAL-CHAT.DATA"))
                        .setName(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.DISPLAY-NAME"))
                        .setLore(Basic.getInst().getConfigFile().getStringList("SETTINGS-CHAT.GLOBAL-CHAT.LORE-DISABLE")).toItemStack();
            }
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
            aquaData.getMessageSystem().setGlobalChat(!aquaData.getMessageSystem().isChatMention());
        }
    }

    private static class ToggleMessagesButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            if (AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId()).getMessageSystem().isMessagesToggled()) {
                return new ItemBuilder(Material.valueOf(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.ITEM")))
                        .setDurability((short) Basic.getInst().getConfigFile().getInt("SETTINGS-CHAT.GLOBAL-CHAT.DATA"))
                        .setName(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.DISPLAY-NAME"))
                        .setLore(Basic.getInst().getConfigFile().getStringList("SETTINGS-CHAT.GLOBAL-CHAT.LORE-ENABLE")).toItemStack();
            } else {
                return new ItemBuilder(Material.valueOf(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.ITEM")))
                        .setDurability((short) Basic.getInst().getConfigFile().getInt("SETTINGS-CHAT.GLOBAL-CHAT.DATA"))
                        .setName(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.DISPLAY-NAME"))
                        .setLore(Basic.getInst().getConfigFile().getStringList("SETTINGS-CHAT.GLOBAL-CHAT.LORE-DISABLE")).toItemStack();
            }
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
            aquaData.getMessageSystem().setGlobalChat(!aquaData.getMessageSystem().isMessagesToggled());
        }
    }

    private static class ToggleMessagesSoundButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            if (AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId()).getMessageSystem().isSoundsEnabled()) {
                return new ItemBuilder(Material.valueOf(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.ITEM")))
                        .setDurability((short) Basic.getInst().getConfigFile().getInt("SETTINGS-CHAT.GLOBAL-CHAT.DATA"))
                        .setName(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.DISPLAY-NAME"))
                        .setLore(Basic.getInst().getConfigFile().getStringList("SETTINGS-CHAT.GLOBAL-CHAT.LORE-ENABLE")).toItemStack();
            } else {
                return new ItemBuilder(Material.valueOf(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.ITEM")))
                        .setDurability((short) Basic.getInst().getConfigFile().getInt("SETTINGS-CHAT.GLOBAL-CHAT.DATA"))
                        .setName(Basic.getInst().getConfigFile().getString("SETTINGS-CHAT.GLOBAL-CHAT.DISPLAY-NAME"))
                        .setLore(Basic.getInst().getConfigFile().getStringList("SETTINGS-CHAT.GLOBAL-CHAT.LORE-DISABLE")).toItemStack();
            }
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
            aquaData.getMessageSystem().setGlobalChat(!aquaData.getMessageSystem().isSoundsEnabled());
        }
    }
}
