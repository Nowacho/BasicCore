package dev.wacho.basic.misc.cosmetics.projectiles.menu;

import dev.wacho.basic.misc.cosmetics.color.chatcolor.menu.ChatColorMenu;
import dev.wacho.basic.misc.cosmetics.death.deathmessage.menu.DeathMessageMenu;
import dev.wacho.basic.misc.cosmetics.effects.menu.EffectsMenu;
import dev.wacho.basic.misc.cosmetics.projectiles.menu.buttons.ProjectilesButton;
import dev.wacho.basic.misc.cosmetics.projectiles.Projectiles;
import dev.wacho.basic.misc.cosmetics.utils.all.SeparatorCategoryButton;
import dev.wacho.basic.misc.cosmetics.utils.all.SeparatorButton;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import dev.wacho.basic.utils.menu.button.CloseButton;
import dev.wacho.basic.utils.menu.button.NextPageButton;
import dev.wacho.basic.utils.menu.pagination.PageButton;
import dev.wacho.basic.utils.menu.pagination.PaginatedMenu;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static dev.wacho.basic.utils.CC.translate;

public class ProjectilesMenu extends Menu {

   @Override
   public String getTitle(Player player) {
      return translate("&8Projectiles ┃ 1/1");
   }

   @Override
   public int size(Player player) {
      return 9 * 6;
   }

   @Override
   public Map<Integer, Button> getButtons(Player player) {
      Map<Integer, Button> buttons = new HashMap<>();
      for (int i = 9; i <= 17; i++) {
         buttons.put(i, new SeparatorButton());
      }

      buttons.put(13, new SeparatorCategoryButton());
      buttons.put(0, new CloseButton());
      buttons.put(7, new NextPageButton());

      buttons.put(2, new EffectsCategoryButton());
      buttons.put(3, new BalloonsCategoryButton());
      buttons.put(4, new ProjectileCategoryButton());
      buttons.put(5, new DeathMessageCategoryButton());
      buttons.put(6, new ChatColorCategoryButton());

      buttons.put(19, new ProjectilesButton(Projectiles.HEART));
      buttons.put(20, new ProjectilesButton(Projectiles.FLAME));
      buttons.put(21, new ProjectilesButton(Projectiles.SLIME));
      buttons.put(22, new ProjectilesButton(Projectiles.NOTE));
      buttons.put(23, new ProjectilesButton(Projectiles.CLOUD));
      buttons.put(24, new ProjectilesButton(Projectiles.SMOKE));
      buttons.put(25, new ProjectilesButton(Projectiles.VILLAGER));
      buttons.put(28, new ProjectilesButton(Projectiles.CRITICAL));
      buttons.put(29, new ProjectilesButton(Projectiles.EXPLOSION));
      buttons.put(30, new ProjectilesButton(Projectiles.FIREWORK));
      buttons.put(31, new ProjectilesButton(Projectiles.LAVA));
      buttons.put(32, new ProjectilesButton(Projectiles.WATER));
      buttons.put(33, new ProjectilesButton(Projectiles.SNOW));
      buttons.put(34, new ProjectilesButton(Projectiles.SPELL));
      buttons.put(37, new ProjectilesButton(Projectiles.COLOURED));

      return buttons;
   }

   private static class EffectsCategoryButton extends Button {

      @Override
      public ItemStack getButtonItem(Player player) {
         return new ItemBuilder(Material.BLAZE_POWDER)
                 .setName("&aEffects")
                 .setLore("&7Equip some particles and show how", "&7cool you are in our lobbies!", "", "&eView category items!").toItemStack();
      }

      @Override
      public void clicked(Player player, ClickType clickType) {
         player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
         new EffectsMenu().openMenu(player);
      }
   }

   private static class BalloonsCategoryButton extends Button {

      @Override
      public ItemStack getButtonItem(Player player) {
         return new ItemBuilder(Material.LEASH)
                 .setName("&aBalloons")
                 .setLore("&7Equip a faithful balloon that", "&7floats around your head!", "", "&eView category items!").toItemStack();
      }

      @Override
      public void clicked(Player player, ClickType clickType) {
         player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
      }
   }

   private static class ProjectileCategoryButton extends Button {

      @Override
      public ItemStack getButtonItem(Player player) {
         return new ItemBuilder(Material.BOW)
                 .setName("&aProjectile")
                 .setLore("&7Equip a wake effect to", "&7make the projectiles you fire", "&7look really amazing!", "", "&eView category items!").toItemStack();
      }

      @Override
      public void clicked(Player player, ClickType clickType) {
         player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
      }
   }

   private static class DeathMessageCategoryButton extends Button {

      @Override
      public ItemStack getButtonItem(Player player) {
         return new ItemBuilder(Material.SKULL_ITEM)
                 .setName("&aDeath Message")
                 .setLore("&7Equip an awesome death", "&7message that will be triggered", "&7every time you die!", "", "&aYou are viewing this category!").toItemStack();
      }

      @Override
      public void clicked(Player player, ClickType clickType) {
         player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
         new DeathMessageMenu().openMenu(player);
      }
   }

   private static class ChatColorCategoryButton extends Button {

      @Override
      public ItemStack getButtonItem(Player player) {
         return new ItemBuilder(Material.BOOK_AND_QUILL)
                 .setName("&aChat Colors")
                 .setLore("&7Equip a color and make your", "&7chat message look different!", "", "&eView category items!").toItemStack();
      }

      @Override
      public void clicked(Player player, ClickType clickType) {
         player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
         new ChatColorMenu().openMenu(player);
      }
   }
}
