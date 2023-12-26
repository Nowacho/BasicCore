package dev.wacho.basic.misc.cosmetics.death.deathmessage;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public enum DeathMessage {

   COMPUTER_NERD("Computer Nerd", "&cComputer Nerd", Material.COMPASS),
   MEMES("Memes", "&eMemes", Material.BOOK),
   PVP_PACK("PvP Pack" ,"&bPvP Pack", Material.NETHER_STAR),
   ADVANCED("Advanced", "&dAdvanced", Material.DIAMOND_SWORD);

   private final String name;
   private final String displayName;
   private final Material material;

   public String getPermission() {
      return "basic.cosmetics.death." + name.toLowerCase().replace(" ", "_");
   }

   public ItemStack getIcon(Player player, PlayerData playerData) {
      if (playerData.getDeathMessage() != null && playerData.getDeathMessage().equals(this)) {
         return new ItemBuilder(material)
                 .setName("&a" + displayName)
                 .setLore("").toItemStack();
      } else if (player.hasPermission(getPermission())) {
         return new ItemBuilder(material)
                 .setName("&a" + displayName)
                 .setLore("&eClick to select the " + name + " tag.").toItemStack();
      }
      else if (player.hasPermission("basic.cosmetics.trail.*")) {
         return new ItemBuilder(material)
                 .setName("&a" + displayName)
                 .setLore("&aYou already have this tag selected.").toItemStack();
      }
      else {
         return new ItemBuilder(Material.INK_SACK)
                 .setName("&a" + displayName)
                 .setDurability(8)
                 .setLore("&cYou do not have permission to use this tag.").toItemStack();
      }
   }
}
