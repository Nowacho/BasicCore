package dev.wacho.basic.misc.cosmetics.tags;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public enum TagsType {

    //Normal Tags

    BI("Bi", "&d❤&5❤&d❤", Material.NAME_TAG),
    DAB("Dab", "&5&l<o/", Material.NAME_TAG),
    LGBT("LGBT", "&c&lL&e&lG&a&lB&b&lT", Material.NAME_TAG),
    PALOMA("Paloma", "&4&lPa&f&llo&4&lma", Material.NAME_TAG),
    NARIGON("Narion", "&b&lNa&f&lr&e&li&f&lg&b&lon", Material.NAME_TAG),
    DNS("DNS", "&b&lDNS", Material.NAME_TAG),
    RAGE("RAGE", "&c&lRage", Material.NAME_TAG),
    MONSTER("Monster Energy", "", Material.NAME_TAG),
    KILLER("Killer", "&4&lK&c&liller", Material.NAME_TAG),
    MONEY("$$$", "&e&l$&6&l$&e&l$", Material.NAME_TAG),
    HACKER("Hacker", "&4&lHACKER", Material.NAME_TAG),
    PAYPAL("Paypal", "&9&lPay&b&lPal", Material.NAME_TAG),
    VERIFIED("Verified", "&a✔", Material.NAME_TAG),
    L("L", "&c&o#L", Material.NAME_TAG),
    FIRST("First", "&b#1", Material.NAME_TAG),
    EZ("EZ", "&b&lEZ", Material.NAME_TAG),

    //Countries Tags
    ARGENTINA("Argentina", "&b&lA&f&lR&b&lG", Material.NAME_TAG),
    CHILE("Chile", "&4&lC&f&lH&1&lL", Material.NAME_TAG),
    URUGUAY("Uruguay", "&e&lU&9&lR&f&lU", Material.NAME_TAG),
    SPAIN("Spain", "&c&lE&e&lS&c&lP", Material.NAME_TAG),
    MEXICO("Mexico", "&2&lM&f&lE&4&lX", Material.NAME_TAG),
    FRANCIA("Francia", "&9&lF&f&lR&4&lA", Material.NAME_TAG),
    JAPAN("Japan", "&f&lJap&4&l❤&f&ln", Material.NAME_TAG),
    PARAGUAY("Paraguay", "&4&lP&f&lG&1&lY", Material.NAME_TAG),
    BOLIVIA("Bolivia", "&4&lB&e&lO&2&lL", Material.NAME_TAG),
    COLOMBIA("Colombia", "&e&lC&9&lO&4&lL", Material.NAME_TAG),
    BRAZIL("Brazil", "&2&lB&e&lR&9&lL", Material.NAME_TAG),
    VENEZUELA("Venezuela", "&e&lV&9&lZ&c&lL", Material.NAME_TAG),
    RD("Republic dominant", "&4&lR&9&lD", Material.NAME_TAG),
    USA("USA", "&4&lU&f&lS&9&lA", Material.NAME_TAG),
    PERU("Peru", "&4&lP&f&lER&4&lU", Material.NAME_TAG),
    CANADA("Canada", "&4&lC&f&lA&4&lN", Material.NAME_TAG),
    GUATEMALA("Guatemala", "&b&lG&f&lT&b&lM", Material.NAME_TAG),
    RUSSIA("Russia", "&f&lR&9&lU&c&lS", Material.NAME_TAG);

    private final String name;
    private final String displayName;
    private final Material material;

    public String getPermission() {
        return "basic.tags." + name.toLowerCase().replace(" ", "_");
    }

    public ItemStack getIcon(Player player, PlayerData playerData) {
        if (playerData.getTagsType() != null && playerData.getTagsType().equals(this)) {
            return new ItemBuilder(material)
                    .setName("&a" + name)
                    .setLore("&7Displayed as: " + getDisplayName() + " " + player.getDisplayName(), "", "&aYou already have this tag selected!", "", "&eClick to deactivate the current tag.").toItemStack();
        } else if (player.hasPermission(getPermission())) {
            return new ItemBuilder(material)
                    .setName("&a" + name)
                    .setLore("&7Displayed as: " + getDisplayName() + " " + player.getDisplayName(), "", "&eClick to select the " + name + " tag.").toItemStack();
        } else if (player.hasPermission("basic.tags.*")) {
            return new ItemBuilder(material)
                    .setName("&a" + name)
                    .setLore("&7Displayed as: " + getDisplayName() + " " + player.getDisplayName(), "", "&eClick to select the " + name + " tag.").toItemStack();
        } else {
            return new ItemBuilder(Material.INK_SACK)
                    .setName("&a" + name)
                    .setDurability(8)
                    .setLore("&7Displayed as: " + getDisplayName() + " " + player.getDisplayName(), "", "&cYou do not have permission to use this tag.").toItemStack();
        }
    }
}
