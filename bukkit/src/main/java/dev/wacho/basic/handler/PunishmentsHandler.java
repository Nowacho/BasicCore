package dev.wacho.basic.handler;

import dev.wacho.basic.BasicAPI;
import dev.wacho.basic.misc.shop.menu.punishment.PunishmentShopMenu;
import dev.wacho.basic.utils.CC;
import me.activated.core.api.player.PlayerData;
import me.activated.core.api.punishment.Punishment;
import me.activated.core.plugin.AquaCore;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;

public class PunishmentsHandler implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

        if (playerData != null) {
            handleBan(event, playerData);
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

        if (playerData != null && playerData.isBanned()) {
            event.setCancelled(true);
            sendBanMessage(player, playerData.getActiveBan());
            new PunishmentShopMenu().openMenu(player);
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

        if (playerData != null && playerData.isBanned()) {
            event.setCancelled(true);
            sendBanMessage(player, playerData.getActiveBan());
            new PunishmentShopMenu().openMenu(player);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

        if (playerData != null && playerData.isBanned()) {
            event.setCancelled(true);
            sendBanMessage(player, playerData.getActiveBan());
            new PunishmentShopMenu().openMenu(player);
        }
    }

    private void handleBan(PlayerLoginEvent event, PlayerData playerData) {
        if (playerData.isBanned()) {
            Punishment activeBan = playerData.getActiveBan();

            if (activeBan != null) {
                if (activeBan.isIPRelative()) {
                    allowEntryToLobby(event.getPlayer());
                } else {
                    allowEntryToLobby(event.getPlayer());
                    sendBanMessage(event.getPlayer(), activeBan);
                }
            }
        }
    }

    private void sendBanMessage(Player player, Punishment punishment) {
        player.sendMessage(CC.translate(""));
        if (punishment.getDurationTime() > 0) {
            player.sendMessage(CC.translate("&cYou are temporarily banned for &4" + punishment.getDurationTime() + "&c from the Velnox Network."));
        } else {
            player.sendMessage(CC.translate("&cYou are permanently banned from the Velnox Network."));
        }
        player.sendMessage(CC.translate(""));
        player.sendMessage(CC.translate("&7Reason: &f" + punishment.getReason()));
        player.sendMessage(CC.translate(""));
        player.sendMessage(CC.translate("&8Unfairly banned? &7You can appeal your punishment by submitting a ticket at &fdiscord.velnox.club&7."));
        player.sendMessage(CC.translate(""));
    }

    private void allowEntryToLobby(Player player) {
        BasicAPI.getToServer(player, "Lobby");
    }
}

