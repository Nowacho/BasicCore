package dev.wacho.basic.misc.cosmetics.color.namecolor.menu.buttons;

import dev.wacho.basic.misc.cosmetics.color.namecolor.MessageColor;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.player.PlayerDataManager;
import dev.wacho.basic.utils.menu.Button;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class MessageColorButton extends Button {

    private MessageColor messageColor;

    public MessageColorButton(MessageColor messageColor) {
        this.messageColor = messageColor;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return messageColor.getIcon(player, PlayerDataManager.getPlayerData(player.getUniqueId()));
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if (player.hasPermission(messageColor.getPermission())) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getMessageColor() != null && playerData.getMessageColor().equals(messageColor)) {
                playNeutral(player);
                return;
            }

            playerData.setMessageColor(messageColor);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 40F, 30L);
        }
        else if (player.hasPermission("pandahub.settings.message_color.*")) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getMessageColor() != null && playerData.getMessageColor().equals(messageColor)) {
                playNeutral(player);
                return;
            }

            playerData.setMessageColor(messageColor);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 40F, 30L);
        }
        else {
            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
        }
    }
}
