package dev.wacho.basic.utils.playerversion.impl;

import dev.wacho.basic.utils.playerversion.IPlayerVersion;
import dev.wacho.basic.utils.playerversion.PlayerVersion;
import org.bukkit.entity.Player;
import us.myles.ViaVersion.api.Via;

public class PlayerVersionViaVersionImpl implements IPlayerVersion {
    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(Via.getAPI().getPlayerVersion(player.getUniqueId()));
    }
}
