package dev.wacho.basic.utils;

import dev.wacho.basic.integrations.PlaceholderAPIHook;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import me.activated.core.utilities.general.StringUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class CC {

    public static Map<ChatColor, String> colorValues = new HashMap<>();

    public static final String SB_BAR = org.bukkit.ChatColor.GRAY.toString() + org.bukkit.ChatColor.STRIKETHROUGH + "----------------------";
    public static final String MENU_BAR = org.bukkit.ChatColor.GRAY.toString() + org.bukkit.ChatColor.STRIKETHROUGH + "----------------------------";
    public static final String DARK_MENU_BAR = org.bukkit.ChatColor.DARK_GRAY.toString() + org.bukkit.ChatColor.STRIKETHROUGH + "--------------------------------";
    public static final String CHAT_BAR = org.bukkit.ChatColor.GRAY.toString() + org.bukkit.ChatColor.STRIKETHROUGH + "------------------------------------------------";

    private List<ChatColor> WOOL_COLORS = Arrays.asList(
            ChatColor.WHITE, ChatColor.GOLD,
            ChatColor.LIGHT_PURPLE, ChatColor.AQUA,
            ChatColor.YELLOW, ChatColor.GREEN,
            ChatColor.LIGHT_PURPLE, ChatColor.DARK_GRAY,
            ChatColor.GRAY, ChatColor.DARK_AQUA,
            ChatColor.DARK_PURPLE, ChatColor.BLUE,
            ChatColor.BLACK, ChatColor.DARK_GREEN,
            ChatColor.RED);

    public int getWoolColorFromChatColor(ChatColor color) {
        if(color == ChatColor.DARK_RED) {
            color = ChatColor.RED;
        }

        if(color == ChatColor.DARK_BLUE) {
            color = ChatColor.BLUE;
        }

        return WOOL_COLORS.indexOf(color);
    }

    public static void broadcast(String message) {
        CC.broadcast(message);
    }
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

    public String translate(String text) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String hexCode = text.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder();

            for (char c : ch) {
                builder.append("&").append(c);
            }

            text = text.replace(hexCode, builder.toString());
            matcher = pattern.matcher(text);
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public String strip(String text) {
        return ChatColor.stripColor(text);
    }

    /*public static String translate(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }*/

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
}