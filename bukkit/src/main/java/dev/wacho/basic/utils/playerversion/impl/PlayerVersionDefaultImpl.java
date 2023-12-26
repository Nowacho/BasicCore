package dev.wacho.basic.utils.playerversion.impl;

import dev.wacho.basic.utils.playerversion.IPlayerVersion;
import dev.wacho.basic.utils.playerversion.PlayerVersion;
import org.bukkit.entity.Player;

public class PlayerVersionDefaultImpl implements IPlayerVersion {
    @Override
    public PlayerVersion getPlayerVersion(Player player) {
        return PlayerVersion.getVersionFromRaw(0);
    }
}
