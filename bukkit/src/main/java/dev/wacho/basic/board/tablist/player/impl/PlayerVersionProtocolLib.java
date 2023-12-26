package dev.wacho.basic.board.tablist.player.impl;

import com.comphenix.protocol.ProtocolLibrary;
import dev.wacho.basic.board.tablist.player.module.IPlayerVersion;
import dev.wacho.basic.board.tablist.player.module.PlayerVersion;
import org.bukkit.entity.Player;

public class PlayerVersionProtocolLib implements IPlayerVersion {

    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(ProtocolLibrary.getProtocolManager().getProtocolVersion(player));
    }
}
