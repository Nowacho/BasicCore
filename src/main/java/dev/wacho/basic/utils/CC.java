package dev.wacho.basic.utils;

import dev.wacho.basic.integrations.PlaceholderAPIHook;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import me.clip.placeholderapi.PlaceholderAPI;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CC {

    @Getter private static String LINE_NORMAL = "&8&m----------------------------------------------";
    @Getter private static String LINE_LIGH = "&8&m-------------------------";

    public static List<String> translateFromArray(List<String> text) {
        List<String> messages = new ArrayList<>();
        for (String string : text)
            messages.add(translateFromString(string));
        return messages;
    }

    public static String translateFromString(String text) {
        return StringEscapeUtils.unescapeJava(ChatColor.translateAlternateColorCodes('&', text));
    }

    public String placeholder(Player player, String text) {
        return translate(PlaceholderAPIHook.isEnabled() ? PlaceholderAPI.setPlaceholders(player, text) : text);
    }

    public List<String> placeholder(Player player, List<String> list) {
        return translate(PlaceholderAPIHook.isEnabled() ? PlaceholderAPI.setPlaceholders(player, list) : list);
    }

    public static String translate(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static void log(String in) {
        Bukkit.getConsoleSender().sendMessage(translate(in));
    }

    public static List<String> translate(List<String> lines) {
        List<String> toReturn = new ArrayList<>();
        for (String line : lines)
            toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        return toReturn;
    }

    public static List<String> translate(String[] lines) {
        List<String> toReturn = new ArrayList<>();
        for (String line : lines) {
            if (line != null)
                toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        return toReturn;
    }

    public static String capitalize(String str) {
        return WordUtils.capitalize(str);
    }
}