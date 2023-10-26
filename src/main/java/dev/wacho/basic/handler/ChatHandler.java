package dev.wacho.basic.handler;

import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener {

    @EventHandler
    private void OnChat (AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (Basic.getInst().getConfigFile().getBoolean("CHAT.ENABLED")) {
            String string = Basic.getInst().getConfigFile().getString("CHAT.FORMAT")
                    .replace("%message%", player.hasPermission("basic.chat.color") ? CC.translate(event.getMessage()) : event.getMessage());

            event.setFormat(CC.translate(CC.placeholder(player, string)));
        }
    }
}
