package dev.wacho.basic.board.scoreboard;

import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.ScoreboardAnimated;
import me.activated.core.api.player.PlayerData;
import me.activated.core.api.punishment.Punishment;
import me.activated.core.plugin.AquaCoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Adapter implements AssembleAdapter {

    private String formatTps(double tps) {
        return (tps > 18.0 ? ChatColor.GREEN : tps > 16.0 ? ChatColor.YELLOW : ChatColor.RED).toString() + Math.min(Math.round(tps * 100.0D) / 100.0D, 20.0D);
    }

    @Override
    public String getTitle(Player player) {
        return Basic.getInst().getScoreboardFile().getBoolean("TITLE-ANIMATED.ENABLED") ?
                ScoreboardAnimated.getTitle() : Basic.getInst().getScoreboardFile().getString("TITLE");
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> scores = new ArrayList<>();
        PlayerData aquaData = AquaCoreAPI.INSTANCE.getPlayerData(player.getUniqueId());

        if (!aquaData.isInStaffMode()) {
            for (String string : Basic.getInst().getScoreboardFile().getStringList("IN-SPAWN")) {

                scores.add(string);
            }
        } else if (aquaData.isBanned()) {
            Punishment activeBan = aquaData.getActiveBan();
            if (activeBan != null) {
                for (String s : Basic.getInst().getScoreboardFile().getStringList("BANNED")) {
                    String formatted = s.replaceAll("%reason%", activeBan.getReason())
                            .replace("%duration%", String.valueOf(activeBan.getDurationTime()));

                    scores.add(formatted);
                }
            }
        }

        List<? extends Player> players = Bukkit.getOnlinePlayers().stream().filter(p -> p.hasPermission("")).collect(Collectors.toList());
        if (aquaData.isInStaffMode()) {
            for (String s : Basic.getInst().getScoreboardFile().getStringList("STAFF")) {
                String formatted = s.replaceAll("%gamemode%", player.getGameMode().name())
                        .replace("%vanish%", (aquaData.isVanished() ? Basic.getInst().getScoreboardFile().getString("VANISH.ENABLED") :
                                Basic.getInst().getScoreboardFile().getString("VANISH.DISABLED")))
                        .replace("%channel%", (AquaCoreAPI.INSTANCE.isStaffChat(player) || AquaCoreAPI.INSTANCE.isAdminChat(player) ?
                                Basic.getInst().getScoreboardFile().getString("CHANNEL.STAFF") : Basic.getInst().getScoreboardFile().getString("CHANNEL.PUBLIC")))
                        .replace("%players%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                        .replace("%staffonline%", String.valueOf(players.size()))
                        .replace("%tps%", formatTps(Bukkit.spigot().getTPS()[0]));

                scores.add(formatted);
            }
        }

        if (!scores.isEmpty()) {
            if (Basic.getInst().getScoreboardFile().getBoolean("BARS-ENABLED")) {
                scores.add(0, "&c" + Basic.getInst().getScoreboardFile().getString("BARS"));
            }
            if (Basic.getInst().getScoreboardFile().getBoolean("FOOTER-ENABLED")) {
                scores.add(" ");
                scores.add(Basic.getInst().getScoreboardFile().getBoolean("FOOTER-ANIMATED.ENABLED") ?
                        ScoreboardAnimated.getFooter() : Basic.getInst().getScoreboardFile().getString("FOOTER"));
            }
            if (Basic.getInst().getScoreboardFile().getBoolean("BARS-ENABLED")) {
                scores.add(scores.size(), "&c" + Basic.getInst().getScoreboardFile().getString("BARS"));
            }
        }

        scores = scores.stream().map(CC::translate).collect(Collectors.toList());
        scores = scores.stream().map(string -> ChatColor.translateAlternateColorCodes('&', string)).collect(Collectors.toList());

        return CC.placeholder(player, scores);
    }
}