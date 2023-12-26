package dev.wacho.basic.misc.cosmetics.color.chatcolor;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor @Getter
public enum ChatColor {

    DARK_RED("Dark Red", "&4", 14),
    RED("Red", "&c", 14),
    ORANGE("Orange", "&6", 1),
    YELLOW("Yellow", "&e", 4),
    DARK_GREEN("Dark Green", "&2", 13),
    GREEN("Green", "&a", 5),
    DARK_BLUE("Dark Blue", "&1", 11),
    BLUE("Blue", "&9", 9),
    DARK_AQUA("Dark Aqua", "&3", 9),
    AQUA("Aqua", "&b", 3),
    PURPLE("Purple", "&5", 10),
    PINK("Pink", "&d", 6),
    WHITE("White", "&f", 0),
    GRAY("Gray", "&7", 8),
    DARK_GRAY("Dark Gray", "&8", 7),
    BLACK("Black", "&0", 15);

    private final String name;
    private final String color;
    private final int data;

    public String getDisplayName() {
        return color + name;
    }

    public String getPermission() {
        return "basic.cosmetics.color." + name.toLowerCase().replace(" ", "_");
    }

    public ItemStack getIcon(Player player, PlayerData playerData) {
        if (playerData.getChatColor() != null && playerData.getChatColor().equals(this)) {
            return new ItemBuilder(Material.WOOL)
                    .setName("&a" + getDisplayName())
                    .setDurability(data)
                    .setLore("&7Change the color of your chat",
                            "&7messages to " + getDisplayName() + "&7.",
                            "",
                            player.getDisplayName() + "&7: " + getColor() + "Hello!",
                            "",
                            "&aYou already have this color selected!").toItemStack();
        } else if (player.hasPermission(getPermission())) {
            return new ItemBuilder(Material.WOOL)
                    .setName("&a" + getDisplayName())
                    .setDurability(data)
                    .setLore("&7Change the color of your chat",
                            "&7messages to " + getDisplayName() + "&7.",
                            "",
                            player.getDisplayName() + "&7: " + getColor() + "Hello!",
                            "",
                            "&eClick to set the color.").toItemStack();
        } else if (player.hasPermission("basic.cosmetics.color.*")) {
            return new ItemBuilder(Material.WOOL)
                    .setName("&a" + getDisplayName())
                    .setDurability(data)
                    .setLore("&7Change the color of your chat",
                            "&7messages to " + getDisplayName() + "&7.",
                            "",
                            player.getDisplayName() + "&7: " + getColor() + "Hello!",
                            "",
                            "&aYou already have this color selected.").toItemStack();
        } else {
            return new ItemBuilder(Material.INK_SACK)
                    .setName("&a" + getDisplayName())
                    .setDurability(8)
                    .setLore("&7Change the color of your chat",
                            "&7messages to " + getDisplayName() + "&7.",
                            "",
                            player.getDisplayName() + "&7: " + getColor() + "Hello!",
                            "",
                            "&cYou do not have permission to use this color.").toItemStack();
        }
    }
}
