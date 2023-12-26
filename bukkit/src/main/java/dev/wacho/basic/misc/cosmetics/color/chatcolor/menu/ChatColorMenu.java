package dev.wacho.basic.misc.cosmetics.color.chatcolor.menu;

import com.google.common.collect.Maps;
import dev.wacho.basic.misc.cosmetics.color.chatcolor.ChatColor;
import dev.wacho.basic.misc.cosmetics.color.chatcolor.menu.buttons.ChatColorButton;
import dev.wacho.basic.misc.cosmetics.color.chatcolor.menu.buttons.ChatColorRemoveButton;
import dev.wacho.basic.misc.cosmetics.death.deathmessage.menu.DeathMessageMenu;
import dev.wacho.basic.misc.cosmetics.effects.menu.EffectsMenu;
import dev.wacho.basic.misc.cosmetics.projectiles.menu.ProjectilesMenu;
import dev.wacho.basic.misc.cosmetics.utils.all.SeparatorButton;
import dev.wacho.basic.misc.cosmetics.utils.all.SeparatorCategoryButton;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import dev.wacho.basic.utils.menu.button.CloseButton;
import dev.wacho.basic.utils.menu.button.NextPageButton;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static dev.wacho.basic.utils.CC.translate;

public class ChatColorMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8ChatColor ┃ 1/1");
    }

    @Override
    public int size(Player player) {
        return 9 * 6;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();
        for (int i = 9; i <= 17; i++) {
            buttons.put(i, new SeparatorButton());
        }

        buttons.put(15, new SeparatorCategoryButton());
        buttons.put(0, new CloseButton());
        buttons.put(7, new NextPageButton());

        buttons.put(2, new EffectsCategoryButton());
        buttons.put(3, new BalloonsCategoryButton());
        buttons.put(4, new ProjectileCategoryButton());
        buttons.put(5, new DeathMessageCategoryButton());
        buttons.put(6, new ChatColorCategoryButton());

        buttons.put(19, new ChatColorButton(ChatColor.DARK_RED));
        buttons.put(20, new ChatColorButton(ChatColor.RED));
        buttons.put(21, new ChatColorButton(ChatColor.ORANGE));
        buttons.put(22, new ChatColorButton(ChatColor.YELLOW));
        buttons.put(23, new ChatColorButton(ChatColor.DARK_GREEN));
        buttons.put(24, new ChatColorButton(ChatColor.GREEN));
        buttons.put(25, new ChatColorButton(ChatColor.DARK_BLUE));
        buttons.put(28, new ChatColorButton(ChatColor.BLUE));
        buttons.put(29, new ChatColorButton(ChatColor.DARK_AQUA));
        buttons.put(30, new ChatColorButton(ChatColor.AQUA));
        buttons.put(31, new ChatColorButton(ChatColor.PURPLE));
        buttons.put(32, new ChatColorButton(ChatColor.PINK));
        buttons.put(33, new ChatColorButton(ChatColor.WHITE));
        buttons.put(34, new ChatColorButton(ChatColor.GRAY));
        buttons.put(37, new ChatColorButton(ChatColor.DARK_GRAY));
        buttons.put(38, new ChatColorButton(ChatColor.BLACK));

        buttons.put(48, new ChatColorRemoveButton());

        setAutoUpdate(true);

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
        }
    }
}
