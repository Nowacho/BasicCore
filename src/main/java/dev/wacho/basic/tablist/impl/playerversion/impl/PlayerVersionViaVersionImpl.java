package dev.wacho.basic.tablist.impl.playerversion.impl;

import dev.wacho.basic.tablist.impl.playerversion.IPlayerVersion;
import dev.wacho.basic.tablist.impl.playerversion.PlayerVersion;
import org.bukkit.entity.Player;
import us.myles.ViaVersion.api.Via;

public class PlayerVersionViaVersionImpl implements IPlayerVersion {
    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(Via.getAPI().getPlayerVersion(player.getUniqueId()));
    }
}
