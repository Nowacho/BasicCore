package dev.wacho.basic.tablist;

import com.google.common.collect.Sets;
import dev.wacho.basic.Basic;
import dev.wacho.basic.tablist.impl.TabListCommons;
import dev.wacho.basic.tablist.impl.utils.SkinTexture;
import dev.wacho.basic.tablist.impl.utils.TabColumn;
import dev.wacho.basic.tablist.impl.utils.TabLayout;
import dev.wacho.basic.tablist.interfaces.IEAdapter;
import dev.wacho.basic.utils.Animation;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.FileConfig;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class TabAdapter implements IEAdapter {

    public static TabType tabType;

    public TabAdapter() {
        TabAdapter.tabType = TabType.getType("NORMAL");
    }

    @Override
    public String getHeader(Player player) {
        if (Basic.getInst().getTabFile().getBoolean("HEADER-ANIMATED.ENABLED")) {
            return CC.translate(Animation.getTabListHeader());
        } else {
            return CC.translate(Basic.getInst().getTabFile().getString("HEADER-TEXT"));
        }
    }

    private String getDirection(Player player) {
        double rot = (player.getLocation().getYaw() - 90.0f) % 360.0f;

        if (rot < 0) {
            rot += 360.0;
        }

        if (0 <= rot && rot < 22.5) {
            return CC.translate("W");
        } else if (22.5 <= rot && rot < 67.5) {
            return CC.translate("NW");
        } else if (67.5 <= rot && rot < 112.5) {
            return CC.translate("N");
        } else if (112.5 <= rot && rot < 157.5) {
            return CC.translate("NE");
        } else if (157.5 <= rot && rot < 202.5) {
            return CC.translate("E");
        } else if (202.5 <= rot && rot < 247.5) {
            return CC.translate("SE");
        } else if (247.5 <= rot && rot < 292.5) {
            return CC.translate("S");
        } else if (292.5 <= rot && rot < 337.5) {
            return CC.translate("SW");
        } else if (337.5 <= rot && rot < 360.0) {
            return CC.translate("W");
        } else {
            return null;
        }
    }

    @Override
    public String getFooter(Player player) {
        if (Basic.getInst().getTabFile().getBoolean("FOOTER-ANIMATED.ENABLED")) {
            return CC.translate(Animation.getTabListFooter());
        } else {
            return CC.translate(Basic.getInst().getTabFile().getString("FOOTER-TEXT"));
        }
    }

    @Override
    public Set<TabLayout> getSlots(Player player) {
        FileConfig tabFile = Basic.getInst().getTabFile();
        Set<TabLayout> tabObjects = Sets.newHashSet();
        if (tabType == TabType.DEFAULT) {
            for (int i = 1; i < 21; i++) {
                tabObjects.add(new TabLayout(TabColumn.LEFT, i,
                        replaceLobby(tabFile.getString("LEFT." + i + ".TEXT"), player),
                        skin(tabFile.getString("LEFT." + i + ".SKIN"), player), 0));
                tabObjects.add(new TabLayout(TabColumn.MIDDLE, i,
                        replaceLobby(tabFile.getString("CENTER." + i + ".TEXT"), player),
                        skin(tabFile.getString("CENTER." + i + ".SKIN"), player), 0));
                tabObjects.add(new TabLayout(TabColumn.RIGHT, i,
                        replaceLobby(tabFile.getString("RIGHT." + i + ".TEXT"), player),
                        skin(tabFile.getString("RIGHT." + i + ".SKIN"), player), 0));
                tabObjects.add(new TabLayout(TabColumn.FAR_RIGHT, i,
                        replaceLobby(tabFile.getString("EXTERNAL-RIGHT." + i + ".TEXT"), player),
                        skin(tabFile.getString("EXTERNAL-RIGHT." + i + ".SKIN"), player), 0));
            }
        } else {

        }
        return tabObjects;
    }

    public String replaceLobby(String string, Player player) {
        string = CC.placeholder(player, string);
        if (string.contains("%player_location%")) {
            string = string.replace("%player_location%", player.getLocation().getBlockX()
                    + ", " + player.getLocation().getBlockZ() + " &7[&f" + this.getDirection(player) + "&7]");
        }

        if (string.contains("%online_players%")) {
            string = string.replace("%online_players%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size()));
        }
        return string;
    }

    @SneakyThrows
    public SkinTexture skin(String string, Player player) {
        SkinTexture skin = TabListCommons.defaultTexture;
        switch (string) {
            /***************
             * HEADS PLAYER
             **************/
            case "%dev.wacho.basic.player%":
                skin = TabListCommons.getSkinData(player.getUniqueId());
                break;
            case "%discord%":
                skin = TabListCommons.DISCORD_TEXTURE;
                break;
            case "%crown%":
                skin = TabListCommons.CROWN_SKIN;
                break;
            case "%warning%":
                skin = TabListCommons.WARNING_SKIN;
                break;
            case "%information%":
                skin = TabListCommons.INFORMATION_SKIN;
                break;
            case "%castle%":
                skin = TabListCommons.CASTLE_SKIN;
                break;
            case "%stats%":
                skin = TabListCommons.STATS_SKIN;
                break;
            case "%ping%":
                skin = TabListCommons.PING_TEXTURE;
                break;
            case "%waring%":
                skin = TabListCommons.WARNING_TEXTURE;
                break;
            case "%teamspeak%":
                skin = TabListCommons.TEAMSPEAK_TEXTURE;
                break;
            case "%twitter%":
                skin = TabListCommons.TWITTER_TEXTURE;
                break;
            case "%store%":
                skin = TabListCommons.STORE_TEXTURE;
                break;
            case "%youtube%":
                skin = TabListCommons.YOUTUBE_TEXTURE;
                break;
            case "%facebook%":
                skin = TabListCommons.FACEBOOK_TEXTURE;
                break;
            case "%clock%":
                skin = TabListCommons.CLOCK_TEXTURE;
                break;
            case "%chest_gold%":
                skin = TabListCommons.CHESTGOLD_TEXTURE;
                break;
            case "%ender_pearl%":
                skin = TabListCommons.ENDERPEARL_TEXTURE;
                break;
            case "%winrar%":
                skin = TabListCommons.WINRAR_TEXTURE;
                break;
            case "%chest%":
                skin = TabListCommons.CHEST_TEXTURE;
                break;
            case "%ender_chest%":
                skin = TabListCommons.ENDERCHEST_TEXTURE;
                break;
            case "%furnace%":
                skin = TabListCommons.FURNANCE_TEXTURE;
                break;
            case "%dirt%":
                skin = TabListCommons.DIRT_TEXTURE;
                break;
            case "%world%":
                skin = TabListCommons.WORLD_TEXTURE;
                break;
            case "%tv%":
                skin = TabListCommons.TV_TEXTURE;
                break;
            case "%beacon%":
                skin = TabListCommons.BEANCON_TEXTURE;
                break;
            case "%monitor%":
                skin = TabListCommons.MONITOR_TEXTURE;
                break;
            case "%compass%":
                skin = TabListCommons.COMPASS_TEXTURE;
                break;
            case "%earth%":
                skin = TabListCommons.EARTH_TEXTURE;
                break;
            case "%sword%":
                skin = TabListCommons.SWORD_TEXTURE;
                break;
            case "%statistic%":
                skin = TabListCommons.STATISTIC_TEXTURE;
                break;
            case "%ghost%":
                skin = TabListCommons.GHOST_TEXTURE;
                break;
            case "%potion%":
                skin = TabListCommons.POTION_TEXTURE;
                break;


            /*************
             * HEADS COMMUN
             **************/
            case "%green%":
                skin = TabListCommons.GREEN_DOT;
                break;
            case "%blue%":
                skin = TabListCommons.BLUE_DOT;
                break;
            case "%dark_blue%":
                skin = TabListCommons.DARK_BLUE_DOT;
                break;
            case "%dark_aqua%":
                skin = TabListCommons.DARK_AQUA_DOT;
                break;
            case "%purple%":
                skin = TabListCommons.DARK_PURPLE_DOT;
                break;
            case "%pink%":
                skin = TabListCommons.LIGHT_PURPLE_DOT;
                break;
            case "%gray%":
                skin = TabListCommons.GRAY_DOT;
                break;
            case "%red%":
                skin = TabListCommons.RED_DOT;
                break;
            case "%yellow%":
                skin = TabListCommons.YELLOW_DOT;
                break;
            case "%dark_green%":
                skin = TabListCommons.DARK_GREEN_DOT;
                break;
            case "%dark_red%":
                skin = TabListCommons.DARK_RED_DOT;
                break;
            case "%gold%":
                skin = TabListCommons.GOLD_DOT;
                break;
            case "%aqua%":
                skin = TabListCommons.AQUA_DOT;
                break;
            case "%white%":
                skin = TabListCommons.WHITE_DOT;
                break;
            case "%dark_gray%":
                skin = TabListCommons.DARK_GRAY;
                break;
            case "%black%":
                skin = TabListCommons.BLACK_DOT;
                break;
            case "%dark_gray_dot%":
                skin = TabListCommons.DARK_GRAY_DOT;
                break;
        }
        return skin;
    }
}