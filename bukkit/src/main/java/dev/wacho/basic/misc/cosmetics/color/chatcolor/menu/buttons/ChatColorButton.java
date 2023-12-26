package dev.wacho.basic.misc.cosmetics.color.chatcolor.menu.buttons;

import dev.wacho.basic.misc.cosmetics.color.chatcolor.ChatColor;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.player.PlayerDataManager;
import dev.wacho.basic.utils.menu.Button;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ChatColorButton extends Button {

    private ChatColor chatColor;

    public ChatColorButton(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return chatColor.getIcon(player, PlayerDataManager.getPlayerData(player.getUniqueId()));
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if (player.hasPermission(chatColor.getPermission())) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getChatColor() != null && playerData.getChatColor().equals(chatColor)) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
                return;
            }

            playerData.setChatColor(chatColor);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 3500L, 3500F);
        }
        else if (player.hasPermission("basic.cosmetics.color.*")) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getChatColor() != null && playerData.getChatColor().equals(chatColor)) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
                return;
            }

            playerData.setChatColor(chatColor);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 3500L, 3500F);
        }
        else {
            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
        }
    }
}
