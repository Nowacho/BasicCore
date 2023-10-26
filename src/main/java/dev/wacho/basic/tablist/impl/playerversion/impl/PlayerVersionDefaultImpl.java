package dev.wacho.basic.tablist.impl.playerversion.impl;

import dev.wacho.basic.tablist.impl.playerversion.IPlayerVersion;
import dev.wacho.basic.tablist.impl.playerversion.PlayerVersion;
import org.bukkit.entity.Player;

public class PlayerVersionDefaultImpl implements IPlayerVersion {
    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(0);
    }
}
