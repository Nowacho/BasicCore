package dev.wacho.basic.board.tablist.player.impl;

import dev.wacho.basic.board.tablist.player.module.PlayerVersion;
import dev.wacho.basic.board.tablist.player.module.IPlayerVersion;
import org.bukkit.entity.Player;
import us.myles.ViaVersion.api.Via;

public class PlayerVersionViaVersion implements IPlayerVersion {

    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(Via.getAPI().getPlayerVersion(player.getUniqueId()));
    }
}
