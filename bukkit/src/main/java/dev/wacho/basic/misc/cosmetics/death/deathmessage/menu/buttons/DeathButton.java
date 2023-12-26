package dev.wacho.basic.misc.cosmetics.death.deathmessage.menu.buttons;

import dev.wacho.basic.misc.cosmetics.death.deathmessage.DeathMessage;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.player.PlayerDataManager;
import dev.wacho.basic.utils.menu.Button;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class DeathButton extends Button {

    private DeathMessage deathMessage;

    public DeathButton(DeathMessage deathMessage) {
        this.deathMessage = deathMessage;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return deathMessage.getIcon(player, PlayerDataManager.getPlayerData(player.getUniqueId()));
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if (player.hasPermission(deathMessage.getPermission())) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getDeathMessage() != null && playerData.getDeathMessage().equals(deathMessage)) {
                playFail(player);
                return;
            }

            playerData.setDeathMessage(deathMessage);
            playSuccess(player);
            player.closeInventory();
        }
        else if (player.hasPermission("basic.cosmetics.death.*")) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getDeathMessage() != null && playerData.getDeathMessage().equals(deathMessage)) {
                playFail(player);
                return;
            }

            playerData.setDeathMessage(deathMessage);
            playSuccess(player);
            player.closeInventory();
        }
        else {
            playFail(player);
        }
    }
}
