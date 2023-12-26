package dev.wacho.basic.misc.cosmetics.effects.menu;

import dev.wacho.basic.misc.cosmetics.color.chatcolor.menu.ChatColorMenu;
import dev.wacho.basic.misc.cosmetics.death.deathmessage.menu.DeathMessageMenu;
import dev.wacho.basic.misc.cosmetics.effects.Effects;
import dev.wacho.basic.misc.cosmetics.effects.menu.buttons.EffectsButton;
import dev.wacho.basic.misc.cosmetics.projectiles.Projectiles;
import dev.wacho.basic.misc.cosmetics.projectiles.menu.ProjectilesMenu;
import dev.wacho.basic.misc.cosmetics.projectiles.menu.buttons.ProjectilesButton;
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

public class EffectsMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8Effects ┃ 1/1");
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

        buttons.put(11, new SeparatorCategoryButton());
        buttons.put(0, new CloseButton());
        buttons.put(7, new NextPageButton());

        buttons.put(2, new EffectsCategoryButton());
        buttons.put(3, new BalloonsCategoryButton());
        buttons.put(4, new ProjectileCategoryButton());
        buttons.put(5, new DeathMessageCategoryButton());
        buttons.put(6, new ChatColorCategoryButton());

        buttons.put(19, new EffectsButton(Effects.HEART));
        buttons.put(20, new EffectsButton(Effects.FLAME));
        buttons.put(21, new EffectsButton(Effects.SLIME));
        buttons.put(22, new EffectsButton(Effects.NOTE));
        buttons.put(23, new EffectsButton(Effects.CLOUD));
        buttons.put(24, new EffectsButton(Effects.SMOKE));
        buttons.put(25, new EffectsButton(Effects.VILLAGER));
        buttons.put(28, new EffectsButton(Effects.CRITICAL));
        buttons.put(29, new EffectsButton(Effects.EXPLOSION));
        buttons.put(30, new EffectsButton(Effects.FIREWORK));
        buttons.put(31, new EffectsButton(Effects.LAVA));
        buttons.put(32, new EffectsButton(Effects.WATER));
        buttons.put(33, new EffectsButton(Effects.SNOW));
        buttons.put(34, new EffectsButton(Effects.SPELL));
        buttons.put(38, new EffectsButton(Effects.SPELL));
        buttons.put(39, new EffectsButton(Effects.SPELL));
        buttons.put(40, new EffectsButton(Effects.SPELL));

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
            new ProjectilesMenu().openMenu(player);
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

