package dev.wacho.basic.handler;

import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener {

    private final Basic plugin = Basic.getInst();

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    private void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (plugin.getConfigFile().getBoolean("CHAT.ENABLED")) {
            event.setCancelled(true);

            String message = player.hasPermission("basic.chat.color") ? CC.translate(event.getMessage()) : CC.strip(event.getMessage());

            String format = plugin.getConfigFile().getString("CHAT.FORMAT")
                    .replace("%player%", player.getDisplayName())
                    .replace("%message%", message);

            for (Player recipient : event.getRecipients()) {
                recipient.sendMessage(CC.placeholder(player, format));
            }
        }
    }
}
