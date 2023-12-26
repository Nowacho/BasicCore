
package dev.wacho.basic.utils;

import dev.wacho.basic.utils.playerversion.PlayerVersion;
import dev.wacho.basic.utils.playerversion.PlayerVersionHandler;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerUtil {

	public static int getPing(final Player player) {
		return ((CraftPlayer)player).getHandle().ping;
	}

	public static PlayerVersion getPlayerVersion(Player player) {
		return PlayerVersionHandler.version.getPlayerVersion(player);
	}
}