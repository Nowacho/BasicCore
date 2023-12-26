package dev.wacho.basic;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.cooldown.Cooldown;
import lombok.Getter;
import me.activated.core.api.punishment.PunishmentType;
import me.activated.core.api.rank.RankData;
import me.activated.core.api.rank.grant.Grant;
import me.activated.core.plugin.AquaCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasicAPI {

    // Server

    public static String getLastServer(Player player) {
        PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());
        if (playerData != null) {
            return playerData.getLastServer();
        }
        return null;
    }

    public static void getToServer(Player player, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("ConnectOther");
        output.writeUTF(player.getName());
        output.writeUTF(server);
        player.sendPluginMessage(Basic.getInst(), "BungeeCord", output.toByteArray());
    }

    // Cooldown

    public static Cooldown getCooldown(String name) {
        return Cooldown.getCooldownMap().get(name);
    }

    public static List<Cooldown> getPlayerCooldowns(Player player) {
        return Cooldown.getCooldownMap().values().stream().filter(check -> check.getLongMap().containsKey(player.getUniqueId())).collect(Collectors.toList());
    }

    // Coins

    public static int getPlayerCoins(Player player) {
        PlayerData data = PlayerData.getPlayerData(player.getUniqueId());
        if (data != null) {
            return data.getCoins();
        }
        return 0;
    }

    public static void setPlayerCoins(Player player, int coins) {
        PlayerData data = PlayerData.getPlayerData(player.getUniqueId());
        if (data != null) {
            data.setCoins(coins);
        }
    }

    public static void addPlayerCoins(Player player, int amount) {
        PlayerData data = PlayerData.getPlayerData(player.getUniqueId());
        if (data != null) {
            data.setCoins(data.getCoins() + amount);
        }
    }

    public static void removePlayerCoins(Player player, int amount) {
        PlayerData data = PlayerData.getPlayerData(player.getUniqueId());
        if (data != null) {
            data.setCoins(data.getCoins() - amount);
        }
    }

    // Name MC

    public static boolean isLiked(Player player) {
        PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());
        if (playerData == null) return false;
        if (playerData.isVote()) {
            return true;
        } else {
            return false;
        }
    }

    // Rank Data - AquaCore

    public static String getRankName(Player player) {
        me.activated.core.api.player.PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());

        if (aquaData == null) return AquaCore.INSTANCE.getRankManagement().getDefaultRank().getName();
        return aquaData.getHighestRank().getName();
    }

    public static String getRankPrefix(Player player) {
        me.activated.core.api.player.PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
        if (aquaData == null) return AquaCore.INSTANCE.getRankManagement().getDefaultRank().getPrefix();
        return aquaData.getHighestRank().getPrefix();
    }

    public static String getRankSuffix(Player player) {
        me.activated.core.api.player.PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
        if (aquaData == null) return AquaCore.INSTANCE.getRankManagement().getDefaultRank().getSuffix();
        return aquaData.getHighestRank().getSuffix();
    }

    public static boolean hasPermission(Player player, String permissionToCheck) {
        if (player.isOp()) return true;
        me.activated.core.api.player.PlayerData aquaData = AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId());
        final List<String> permissionsList = new ArrayList<>();
        aquaData.getPermissions().forEach(permission -> permissionsList.add(String.valueOf(permission)));
        for (Grant grant : aquaData.getActiveGrants()) {
            RankData rank = grant.getRank();
            if (rank != null) {
                rank.getPermissions().forEach(permission -> permissionsList.add(String.valueOf(permission)));
            }
        }
        return permissionsList.stream().anyMatch(permission -> permission.equalsIgnoreCase(permissionToCheck));
    }

    public static boolean isBanned(Player player) {
        return AquaCore.INSTANCE.getPlayerManagement().getPlayerData(player.getUniqueId()).getActivePunishment(PunishmentType.BAN) != null;
    }
}
