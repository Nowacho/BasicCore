package dev.wacho.basic.handler.menu;

import dev.wacho.basic.Basic;
import dev.wacho.basic.handler.menu.settings.SettingsGlobalMenu;
import dev.wacho.basic.misc.cosmetics.utils.all.SeparatorButton;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class SettingsMenu2 extends Menu {

    @Override
    public String getTitle(Player player) {
        return Basic.getInst().getConfig().getString("SETTINGS.TITLE");
    }

    @Override
    public int size(Player player) {
        return Basic.getInst().getConfig().getInt("SETTINGS.SIZE") * 9;

    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        for (int i = 9; i <= 17; i++) {
            buttons.put(i, new SeparatorButton());
        }

        if (Basic.getInst().getConfigFile().getBoolean("SETTINGS.TAB.ENABLED"))
            buttons.put(Basic.getInst().getConfigFile().getInt("SETTINGS.TAB.SLOT"), new MessagesVisibleButton());

        return buttons;
    }

    private static class MessagesVisibleButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            if (Basic.getInst().getProfileManager().getByUuid(player.getUniqueId()).getSettings().isMessagesVisible()) {
                return new ItemBuilder(Material.valueOf(Basic.getInst().getConfigFile().getString("SETTINGS.MESSAGES-VISIBLE.ITEM")))
                        .setDurability((short) Basic.getInst().getConfigFile().getInt("SETTINGS.MESSAGES-VISIBLE.DATA"))
                        .setName(Basic.getInst().getConfigFile().getString("SETTINGS.MESSAGES-VISIBLE.DISPLAY-NAME"))
                        .setLore(Basic.getInst().getConfigFile().getStringList("SETTINGS.MESSAGES-VISIBLE.LORE-ENABLE")).toItemStack();
            } else {
                return new ItemBuilder(Material.valueOf(Basic.getInst().getConfigFile().getString("SETTINGS.MESSAGES-VISIBLE.ITEM")))
                        .setDurability((short) Basic.getInst().getConfigFile().getInt("SETTINGS.MESSAGES-VISIBLE.DATA"))
                        .setName(Basic.getInst().getConfigFile().getString("SETTINGS.MESSAGES-VISIBLE.DISPLAY-NAME"))
                        .setLore(Basic.getInst().getConfigFile().getStringList("SETTINGS.MESSAGES-VISIBLE.LORE-DISABLE")).toItemStack();
            }
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.performCommand(Basic.getInst().getConfigFile().getString("SETTINGS.MESSAGES-VISIBLE.COMMAND"));
        }
    }
}

