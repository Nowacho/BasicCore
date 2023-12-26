package dev.wacho.basic.board.tablist;

import dev.wacho.basic.Basic;
import dev.wacho.basic.board.tablist.player.PlayerTablist;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;

public class TablistListener implements Listener {

    private final Basic plugin;

    public TablistListener(Basic plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        Tablist.getInstance().getPlayerTablist().put(event.getPlayer().getUniqueId(), new PlayerTablist(plugin, event.getPlayer()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        handleDisconnect(event.getPlayer());
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerKick(PlayerKickEvent event) {
        handleDisconnect(event.getPlayer());
    }

    private void handleDisconnect(Player player) {
        Team team = player.getScoreboard().getTeam("\\u000181");

        if (team != null) {
            team.unregister();
        }

        Tablist.getInstance().getPlayerTablist().remove(player.getUniqueId());
    }
}
