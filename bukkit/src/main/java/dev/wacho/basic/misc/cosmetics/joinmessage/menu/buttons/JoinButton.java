package dev.wacho.basic.misc.cosmetics.joinmessage.menu.buttons;

import dev.wacho.basic.misc.cosmetics.joinmessage.JoinMessage;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.player.PlayerDataManager;
import dev.wacho.basic.utils.menu.Button;
import lombok.RequiredArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class JoinButton extends Button {

    private JoinMessage joinMessage;

    public JoinButton(JoinMessage joinMessage) {
        this.joinMessage = joinMessage;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return joinMessage.getIcon(player, PlayerDataManager.getPlayerData(player.getUniqueId()));
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if (player.hasPermission(joinMessage.getPermission())) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getJoinMessage() != null && playerData.getJoinMessage().equals(joinMessage)) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
                return;
            }

            playerData.setJoinMessage(joinMessage);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 40F, 30L);
        }
        else if (player.hasPermission("basic.cosmetics.join.*")) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getJoinMessage() != null && playerData.getJoinMessage().equals(joinMessage)) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
                return;
            }

            playerData.setJoinMessage(joinMessage);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 40F, 30L);
            player.closeInventory();
        }
        else {
            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
        }
    }
}