package dev.wacho.proxy.handler;

import dev.wacho.proxy.BasicProxy;
import dev.wacho.proxy.player.PlayerDataManager;
import dev.wacho.proxy.util.ColorUtil;
import me.activated.core.api.player.PlayerData;
import me.activated.core.plugin.AquaCore;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class VipChatHandler implements Listener {

    @EventHandler
    public void onChat(ChatEvent event) {
        if (event.getMessage().startsWith("/")) {
            return;
        }
        if (!((ProxiedPlayer) event.getSender()).hasPermission("basic.command.vip")) {
            return;
        }
        if (PlayerDataManager.getInstance().getplayerData(((ProxiedPlayer) event.getSender()).getUniqueId()) == null) {
            return;
        }
        if (!PlayerDataManager.getInstance().getplayerData(((ProxiedPlayer) event.getSender()).getUniqueId()).isvip()) {
            return;
        }

        ProxyServer.getInstance().getPlayers().forEach(vip -> {
            PlayerData playerData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(vip.getUniqueId());

            if (vip.hasPermission("basic.command.vip")) {
                vip.sendMessage(ColorUtil.colorize(playerData.getHighestRank().getPrefix() + ((ProxiedPlayer) event.getSender()).getName() + "&7: " + event.getMessage()));
                /*BasicProxy.getInst().getConfig().getStringList("CHAT.VIP.FORMAT").forEach(message -> vip.sendMessage(ColorUtil.colorize(message
                        .replace("%player_name%", ((ProxiedPlayer) event.getSender()).getName())
                        .replace("%server%", ((ProxiedPlayer) event.getSender()).getServer().getInfo().getName())
                        .replace("%message%", event.getMessage())
                        .replace("%aqua_rank%", ColorUtil.colorize(playerData.getHighestRank().getPrefix())))));*/
            }
        });
        event.setCancelled(true);
    }
}