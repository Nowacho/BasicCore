package dev.wacho.basic.misc.cosmetics.projectiles.menu.buttons;

import dev.wacho.basic.misc.cosmetics.projectiles.Projectiles;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.player.PlayerDataManager;
import dev.wacho.basic.utils.menu.Button;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ProjectilesButton extends Button {

    private Projectiles projectiles;

    public ProjectilesButton(Projectiles projectiles) {
        this.projectiles = projectiles;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return projectiles.getIcon(player, PlayerDataManager.getPlayerData(player.getUniqueId()));
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if (player.hasPermission(projectiles.getPermission())) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getProjectiles() != null && playerData.getProjectiles().equals(projectiles)) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
                return;
            }

            playerData.setProjectiles(projectiles);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 40F, 30L);
            player.closeInventory();
        } else if (player.hasPermission("basic.cosmetics.trail.*")) {
            PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

            if (playerData.getProjectiles() != null && playerData.getProjectiles().equals(projectiles)) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
                return;
            }

            playerData.setProjectiles(projectiles);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 40F, 30L);
            player.closeInventory();
        }
        else {
            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
        }
    }
}
