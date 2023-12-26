package dev.wacho.proxy.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;

@UtilityClass
public class ColorUtil {

	private final List<ChatColor> WOOL_COLORS = Arrays.asList(
			ChatColor.WHITE, ChatColor.GOLD,
			ChatColor.LIGHT_PURPLE, ChatColor.AQUA,
			ChatColor.YELLOW, ChatColor.GREEN,
			ChatColor.LIGHT_PURPLE, ChatColor.DARK_GRAY,
			ChatColor.GRAY, ChatColor.DARK_AQUA,
			ChatColor.DARK_PURPLE, ChatColor.BLUE,
			ChatColor.BLACK, ChatColor.DARK_GREEN,
			ChatColor.RED
			);
	
	public String colorize(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}
	
	public List<String> colorize(Iterable<? extends String> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).filter(Objects::nonNull).map(ColorUtil::colorize).collect(Collectors.toList());
    }

    public List<String> colorize(List<String> list) {
        List<String> buffered = new ArrayList<String>();
        
        list.forEach(code -> buffered.add(colorize(code)));
        return buffered;
    }

    public String[] colorize(String... strings) {
    	return colorize(Arrays.asList(strings)).stream().toArray(String[]::new);
    }
    
    public ChatColor chatColorFromString(String value) {
    	
    	try {
			return ChatColor.valueOf(value.toUpperCase());
		} catch (Exception e) { }
    	
    	return ChatColor.WHITE;
    }
    
    public ChatColor chatColorFromCode(String code) {
    	if (code.equalsIgnoreCase("&1") || code.equalsIgnoreCase("§1")) {
            return ChatColor.DARK_BLUE;
        } else if (code.equalsIgnoreCase("&2") || code.equalsIgnoreCase("§2")) {
            return ChatColor.DARK_GREEN;
        } else if (code.equalsIgnoreCase("&3") || code.equalsIgnoreCase("§3")) {
            return ChatColor.BLUE;
        } else if (code.equalsIgnoreCase("&4") || code.equalsIgnoreCase("§4")) {
            return ChatColor.DARK_RED;
        } else if (code.equalsIgnoreCase("&5") || code.equalsIgnoreCase("§5")) {
            return ChatColor.DARK_PURPLE;
        } else if (code.equalsIgnoreCase("&6") || code.equalsIgnoreCase("§6")) {
            return ChatColor.GOLD;
        } else if (code.equalsIgnoreCase("&7") || code.equalsIgnoreCase("§7")) {
            return ChatColor.GRAY;
        } else if (code.equalsIgnoreCase("&8") || code.equalsIgnoreCase("§8")) {
            return ChatColor.DARK_GRAY;
        } else if (code.equalsIgnoreCase("&9") || code.equalsIgnoreCase("§9")) {
            return ChatColor.DARK_AQUA;
        } else if (code.equalsIgnoreCase("&a") || code.equalsIgnoreCase("§a")) {
            return ChatColor.GREEN;
        } else if (code.equalsIgnoreCase("&b") || code.equalsIgnoreCase("§b")) {
            return ChatColor.AQUA;
        } else if (code.equalsIgnoreCase("&c") || code.equalsIgnoreCase("§c")) {
            return ChatColor.RED;
        } else if (code.equalsIgnoreCase("&d") || code.equalsIgnoreCase("§d")) {
            return ChatColor.LIGHT_PURPLE;
        } else if (code.equalsIgnoreCase("&e") || code.equalsIgnoreCase("§e")) {
            return ChatColor.YELLOW;
        }

        return ChatColor.WHITE;
    }
    
    public int getWoolColorFromChatColor(ChatColor color) {
		if(color == ChatColor.DARK_RED) {
			color = ChatColor.RED;
		}
		
		if(color == ChatColor.DARK_BLUE) {
			color = ChatColor.BLUE;
		}
		
		return WOOL_COLORS.indexOf(color);
	}
}
