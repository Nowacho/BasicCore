package dev.wacho.basic.misc.cosmetics.effects.menu.buttons;

import dev.wacho.basic.misc.cosmetics.effects.Effects;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.player.PlayerDataManager;
import dev.wacho.basic.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class EffectsButton extends Button {

    private Effects effects;

    public EffectsButton(Effects effects) {
        this.effects = effects;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return effects.getIcon(player, PlayerDataManager.getPlayerData(player.getUniqueId()));
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if (player.hasPermission(effects.getPermission())) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getEffects() != null && playerData.getEffects().equals(effects)) {
                playFail(player);
                return;
            }

            playerData.setEffects(effects);
            playSuccess(player);
            player.closeInventory();
        } else if (player.hasPermission("basic.cosmetics.effects.*")) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getEffects() != null && playerData.getEffects().equals(effects)) {
                playFail(player);
                return;
            }

            playerData.setEffects(effects);
            playSuccess(player);
            player.closeInventory();
        }
        else {
            playFail(player);
        }
    }
}
