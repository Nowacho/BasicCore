package dev.wacho.basic.misc.cosmetics.projectiles;

import dev.wacho.basic.Basic;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public enum Projectiles {

   HEART("Heart", "&4Heart", "&7IDK", Material.REDSTONE, Effect.HEART),
   FLAME("Flame", "&6Flame", "&7IDK", Material.BLAZE_POWDER, Effect.FLAME),
   SLIME("Slime", "&aSlime", "&7IDK", Material.SLIME_BALL, Effect.SLIME),
   NOTE("Note", "&9Note", "", Material.NOTE_BLOCK, Effect.NOTE),
   CLOUD("Cloud", "&fCloud", "",  Material.WEB, Effect.CLOUD),
   SMOKE("Smoke", "&7Smoke", "", Material.FIREWORK_CHARGE, Effect.LARGE_SMOKE),
   VILLAGER("Villager", "&aVillager", "", Material.EMERALD, Effect.HAPPY_VILLAGER),
   CRITICAL("Critical", "&5Critical", "", Material.EXP_BOTTLE, Effect.MAGIC_CRIT),
   EXPLOSION("Explosion", "&4Explosion", "", Material.TNT, Effect.EXPLOSION),
   FIREWORK("Firework", "&fFirework", "", Material.FIREWORK, Effect.FIREWORKS_SPARK),
   LAVA("Lava", "&6Lava", "", Material.LAVA_BUCKET, Effect.LAVADRIP),
   WATER("Water", "&9Water", "", Material.WATER_BUCKET, Effect.WATERDRIP),
   SNOW("Snow", "&fSnow", "", Material.SNOW, Effect.SNOWBALL_BREAK),
   SPELL("Spell", "&bSpell", "", Material.POTION, Effect.SPELL),
   COLOURED("Coloured", "&dColoured", "", Material.NETHER_STAR, Effect.COLOURED_DUST);

   private final String name;
   private final String displayName;
   private final String description;
   private final Material material;
   private final Effect effect;

   public void playEffect(Player player, Projectile projectile) {
      Location location = projectile.getLocation();
      player.getWorld().spigot().playEffect(location, effect, 26, 0, 0.2F, 0.5F, 0.2F, 0.2F, 1, 387);

      Bukkit.getScheduler().runTaskTimer(Basic.getInst(), () -> {
         if (!projectile.isValid() || projectile.isOnGround() || projectile.isDead()) {
            return;
         }

         Location particleLocation = projectile.getLocation();
         player.getWorld().spigot().playEffect(particleLocation, effect, 26, 0, 0.2F, 0.5F, 0.2F, 0.2F, 1, 387);
      }, 0L, 1L);
   }

   public String getPermission() {
      return "basic.cosmetics.projectile." + name.toLowerCase().replace(" ", "_");
   }

   public ItemStack getIcon(Player player, PlayerData playerData) {
      if (playerData.getProjectiles() != null && playerData.getProjectiles().equals(this)) {
         return new ItemBuilder(material)
                 .setName("&a" + displayName)
                 .setLore(description, "", "&aYou already have this tag selected!", "", "&eClick to deactivate the current effect.").toItemStack();
      } else if (player.hasPermission(getPermission())) {
         return new ItemBuilder(material)
                 .setName("&a" + displayName)
                 .setLore(description, "", "&eClick to select the " + name + " effect.").toItemStack();
      }
      else if (player.hasPermission("basic.cosmetics.trail.*")) {
         return new ItemBuilder(material)
                 .setName("&a" + displayName)
                 .setLore(description, "", "&aYou already have this tag selected.").toItemStack();
      }
      else {
         return new ItemBuilder(Material.INK_SACK)
                 .setName("&a" + displayName)
                 .setDurability(8)
                 .setLore(description, "", "&cYou do not have permission to use this effect.").toItemStack();
      }
   }
}
