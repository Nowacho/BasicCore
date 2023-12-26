package dev.wacho.basic.handler.hotbar;

import dev.wacho.basic.Basic;
import dev.wacho.basic.misc.hotbar.CustomHotbar;
import dev.wacho.basic.misc.hotbar.CustomHotbarManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class CustomHotbarHandler implements Listener {

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        if (Basic.getInst() != null) {
            CustomHotbarManager customHotbarManager = Basic.getInst().getCustomHotbarManager();
            if (customHotbarManager != null) {
                Player player = playerJoinEvent.getPlayer();
                if (player != null) {
                    customHotbarManager.giveHotbar(player);
                }
            }
        }
    }

    @EventHandler
    private void onHotbarInteract(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;

        CustomHotbar customHotbar = Basic.getInst().getCustomHotbarManager().getHotbar(event.getItem());

        if (customHotbar == null) return;

        event.setCancelled(true);

        for (String string : customHotbar.getCommands()) {
            event.getPlayer().performCommand(string);
        }
    }
}