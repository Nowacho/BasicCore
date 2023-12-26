package dev.wacho.basic.integrations;

import dev.wacho.basic.Basic;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.Utils;
import dev.wacho.basic.utils.lang.Lang;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;

public class PlaceholderAPIExpansion extends PlaceholderExpansion {

    private final Basic plugin;

    public PlaceholderAPIExpansion(Basic plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @NotNull String getAuthor() {
        return "NoWacho";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "Basic";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (player == null) return "";

        //%basic_player_coins%
        if (identifier.equals("player_coins")) {
            PlayerData playerData = PlayerData.getPlayerData(player);
            return String.valueOf(playerData.getCoins());
        }

        //%basic_player_tag%
        if (identifier.equals("player_tag")) {
            PlayerData playerData = PlayerData.getPlayerData(player);
            return playerData.getTagsType() == null ? "" : playerData.getTagsType().getDisplayName();
        }

        //%basic_namemc_likes%
        if (identifier.equals("namemc_likes")) {
            try {
                return String.valueOf(Utils.getNumLikes());
            } catch (IOException e) {
                e.printStackTrace();
                return "§cError when getting likes";
            }
        }

        //%basic_namemc_status%
        if (identifier.equals("namemc_status")) {
            if (Utils.checkPlayerVote(player.getUniqueId())) {
                return CC.translate("&aYou have already liked the server");
            } else {
                return CC.translate("&eLeave your like in namemc to help the server");
            }
        }

        return null;
    }
}
