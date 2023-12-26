package dev.wacho.basic.handler;

import dev.wacho.basic.Basic;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class LaunchPadHandler implements Listener {

    private final Basic plugin = Basic.getInst();

    @EventHandler
    public void onUseLaunch(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (!plugin.getConfigFile().getBoolean("LAUNCH-PAD.ENABLED")) return;
        if (player.getLocation().getBlock().getType() == Material.valueOf(plugin.getConfigFile().getString("LAUNCH-PAD.MATERIAL"))) {
            player.setVelocity(player.getLocation().getDirection()
                    .multiply(plugin.getConfigFile().getDouble("LAUNCH-PAD.JUMP.MULTIPLY"))
                    .setY(plugin.getConfigFile().getDouble("LAUNCH-PAD.JUMP.HEIGHT")));
            if (plugin.getConfigFile().getBoolean("LAUNCH-PAD.SOUND-ENABLED")) {
                player.playSound(player.getLocation(), Sound.valueOf(plugin.getConfigFile().getString("LAUNCH-PAD.SOUND")), 2.0f, 2.0f);
            }
        }
    }
}
