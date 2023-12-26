package dev.wacho.basic.player;

import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class PlayerDataLoad implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        if (!Basic.getInst().isJoined()) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage("§cPlease wait to server load.");
            return;
        }

        Player player = Bukkit.getPlayer(event.getUniqueId());
        if (player != null && player.isOnline()) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(CC.translate("&cYou tried to login too quickly after disconnecting.\nTry again in a few seconds."));
            Basic.getInst().getServer().getScheduler().runTask(Basic.getInst(), () -> player.kickPlayer(CC.translate("&cDuplicate login :/.")));
            return;
        }

        PlayerData playerData = PlayerData.getPlayerData(event.getUniqueId());
        if (playerData == null) {
            playerData = PlayerData.createPlayerData(event.getUniqueId(), event.getName());
        }
        playerData.saveData();
    }


    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        PlayerData playerData = PlayerData.getPlayerData(event.getPlayer().getUniqueId());
        if (playerData == null) {
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage("§cAn error has ocurred while loading your profile. Please reconnect.");
            return;
        }

        if (!playerData.isDataLoaded()) {
            playerData.saveData();
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage("§cAn error has ocurred while loading your profile. Please reconnect.");
        }
    }


    private void handledSaveDate(Player player) {
        PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());
        if (playerData != null) {
            playerData.setLastServerTime(System.currentTimeMillis());
            playerData.removeData();
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        handledSaveDate(event.getPlayer());
    }

    @EventHandler
    public void onPlayerKickEvent(PlayerKickEvent event) {
        handledSaveDate(event.getPlayer());
    }
}
