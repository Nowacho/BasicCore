package dev.wacho.basic.misc.cosmetics.joinmessage;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public enum JoinMessage {

    SERVER("Server", "&cServer Join Message", "&7Announces your entrance to a gamemode!",
            "&e has joined the lobby.", Material.IRON_PICKAXE),
    DEFAULT("Vanilla", "&cVanilla Join Message", "&7Announces your entrance to a gamemode!",
            "&e joined the game.", Material.POTION),
    ROBLOX("Roblox", "&cRoblox Join Message", "&7Announces your entrance to a gamemode!",
            "&e is now playing &cRoblox&e.", Material.GOLDEN_APPLE),
    PARTY("Party is Over", "&cParty is Over Join Message", "&7Announces your entrance to a gamemode!",
            "&c has joined. Party's over.", Material.BLAZE_POWDER),
    OP("OP", "&cOP Join Message", "&7Announces your entrance to a gamemode!",
            "&6 just joined. Seems OP, please nerf.", Material.MUSHROOM_SOUP),
    VELNOX("Velnox Lover", "&cVelnox Lover Join Message", "&7Announces your entrance to a gamemode!",
            "&f has just earned the achievement &5[Velnox Lover]", Material.NETHER_STAR);
    //Test
    //SPAWN("Spawn Join Message", "&cSpawn Join Message", "&7Announces your entrance to a gamemode!", "&e A <PlayerName> &e has spawned in the server!", Material.FLINT_AND_STEEL),
    //CHEATING("Cheater Join Message", "&cCheater Join Message", "&7Announces your entrance to a gamemode!", "&c Cheater <playerName> has joined, report him!", Material.DIAMOND_SWORD);

    private final String name;
    private final String displayName;
    private final String description;
    private final String message;
    private final Material material;

    public String getPermission() {
        return "basic.cosmetics.join." + name.toLowerCase().replace(" ", "_");
    }

    public ItemStack getIcon(Player player, PlayerData playerData) {
        if (playerData.getJoinMessage() != null && playerData.getJoinMessage().equals(this)) {
            return new ItemBuilder(material)
                    .setName("&a" + displayName)
                    .setLore(description,
                            "",
                            player.getDisplayName() + message,
                            "",
                            "&aYou already have this join message selected!").toItemStack();
        } else if (player.hasPermission(getPermission())) {
            return new ItemBuilder(material)
                    .setName("&a" + displayName)
                    .setLore(description,
                            "",
                            player.getDisplayName() + message,
                            "",
                            "&eClick to select the " + name + " join message.").toItemStack();
        } else if (player.hasPermission("basic.cosmetics.join.*")) {
            return new ItemBuilder(material)
                    .setName("&a" + displayName)
                    .setLore(description,
                            "",
                            player.getDisplayName() + message,
                            "",
                            "&aYou already have this join message selected.").toItemStack();
        } else {
            return new ItemBuilder(material)
                    .setName("&a" + displayName)
                    .setLore(description,
                            "",
                            player.getDisplayName() + message,
                            "",
                            "&cYou do not have permission to use this join message.").toItemStack();
        }
    }
}
