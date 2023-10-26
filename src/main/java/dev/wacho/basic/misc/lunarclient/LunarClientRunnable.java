package dev.wacho.basic.misc.lunarclient;

import com.lunarclient.bukkitapi.LunarClientAPI;
import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.CC;
import lombok.Setter;
import org.bukkit.entity.Player;

@Setter
public class LunarClientRunnable implements Runnable {

    private final Basic plugin;

    public LunarClientRunnable(Basic plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : LunarClientAPI.getInstance().getPlayersRunningLunarClient()) {
            for (Player viewer : LunarClientAPI.getInstance().getPlayersRunningLunarClient()) {
                LunarClientAPI.getInstance().overrideNametag(player, CC.placeholder(player, plugin.getConfigFile().getStringList("NAME-TAGS")), viewer);
            }
        }
    }
}
