package dev.wacho.basic.misc.cosmetics.effects;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public enum Effects {

    HEART("Heart", "&4Heart", "", Material.REDSTONE, Effect.HEART),
    FLAME("Flame", "&6Flame", "", Material.BLAZE_POWDER, Effect.FLAME),
    SLIME("Slime", "&aSlime", "", Material.SLIME_BALL, Effect.SLIME),
    NOTE("Note", "&9Note", "&7Don't buy the rhythm!", Material.NOTE_BLOCK, Effect.NOTE),
    CLOUD("Cloud", "&fCloud", "&7Love is the master key that opens\n&7the doors to happiness.", Material.WEB, Effect.CLOUD),
    SMOKE("Smoke", "&7Smoke", "", Material.FIREWORK_CHARGE, Effect.SMALL_SMOKE),
    VILLAGER("Villager", "&aVillager", "&7Green sparkles surround you!", Material.EMERALD, Effect.HAPPY_VILLAGER),
    CRITICAL("Critical", "&5Critical", "", Material.EXP_BOTTLE, Effect.MAGIC_CRIT),
    EXPLOSION("Explosion", "&4Explosion", "", Material.TNT, Effect.EXPLOSION),
    FIREWORK("Firework", "&fFirework", "", Material.FIREWORK, Effect.FIREWORKS_SPARK),
    LAVA("Lava", "&6Lava", "", Material.LAVA_BUCKET, Effect.LAVADRIP),
    WATER("Water", "&9Water", "", Material.WATER_BUCKET, Effect.WATERDRIP),
    SNOW("Snow", "&fSnow", "", Material.SNOW, Effect.SNOW_SHOVEL),
    SPELL("Spell", "&bSpell", "", Material.POTION, Effect.SPELL);

    private final String name;
    private final String displayName;
    private final String description;
    private final Material material;
    private final Effect effect;

    public void playEffect(Player player) {
        Location playerLocation = player.getLocation();
        player.getWorld().spigot().playEffect(playerLocation, effect, 26, 0, 0.2F,
                0.5F, 0.2F, 0.2F, 1, 387);
    }

    public String getPermission() {
        return "basic.cosmetics.effects." + name.toLowerCase().replace(" ", "_");
    }

    public ItemStack getIcon(Player player, PlayerData playerData) {
        if (playerData.getEffects() != null && playerData.getEffects().equals(this)) {
            return new ItemBuilder(material)
                    .setName("&a" + displayName)
                    .setLore("").toItemStack();
        } else if (player.hasPermission(getPermission())) {
            return new ItemBuilder(material)
                    .setName("&a" + displayName)
                    .setLore(description, "", "&eClick to select the " + name + " effect.").toItemStack();
        }
        else if (player.hasPermission("basic.cosmetics.effects.*")) {
            return new ItemBuilder(material)
                    .setName("&a" + displayName)
                    .setLore(description, "", "&aYou already have this effect selected.").toItemStack();
        }
        else {
            return new ItemBuilder(Material.INK_SACK)
                    .setName("&a" + displayName)
                    .setDurability(8)
                    .setLore(description, "", "&cYou do not have permission to use this effect.").toItemStack();
        }
    }
}
