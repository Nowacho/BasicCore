package dev.wacho.basic.misc.cosmetics.tags.menus;

import com.cryptomorin.xseries.XMaterial;
import dev.wacho.basic.misc.cosmetics.tags.menus.buttons.TagsButton;
import dev.wacho.basic.misc.cosmetics.tags.TagsType;
import dev.wacho.basic.misc.cosmetics.tags.menus.buttons.TagsRemoveButton;
import dev.wacho.basic.misc.cosmetics.utils.tags.SeparatorCategoryTagsButton;
import dev.wacho.basic.misc.cosmetics.utils.tags.SeparatorTagsButton;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import dev.wacho.basic.utils.menu.button.CloseButton;
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

public class CountriesTagsMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8Countries Tags ┃ 1/1");
    }

    @Override
    public int size(Player player) {
        return 9 * 6;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        for (int i = 9; i <= 17; i++) {
            buttons.put(i, new SeparatorTagsButton());
        }

        buttons.put(12, new SeparatorCategoryTagsButton());
        buttons.put(2, new NormalTagCategoryButton());
        buttons.put(3, new CountriesTagCategoryButton());
        buttons.put(4, new SpecialsTagCategoryButton());
        buttons.put(5, new PartnerTagCategoryButton());
        buttons.put(6, new CustomizableTagCategoryButton());

        buttons.put(0, new CloseButton());
        buttons.put(48, new TagsRemoveButton());

        buttons.put(19, new TagsButton(TagsType.ARGENTINA));
        buttons.put(20, new TagsButton(TagsType.CHILE));
        buttons.put(21, new TagsButton(TagsType.URUGUAY));
        buttons.put(22, new TagsButton(TagsType.SPAIN));
        buttons.put(23, new TagsButton(TagsType.MEXICO));
        buttons.put(24, new TagsButton(TagsType.FRANCIA));
        buttons.put(25, new TagsButton(TagsType.JAPAN));
        buttons.put(28, new TagsButton(TagsType.PARAGUAY));
        buttons.put(29, new TagsButton(TagsType.BOLIVIA));
        buttons.put(30, new TagsButton(TagsType.COLOMBIA));
        buttons.put(31, new TagsButton(TagsType.BRAZIL));
        buttons.put(32, new TagsButton(TagsType.VENEZUELA));
        buttons.put(33, new TagsButton(TagsType.RD));
        buttons.put(34, new TagsButton(TagsType.USA));
        buttons.put(37, new TagsButton(TagsType.PERU));
        buttons.put(38, new TagsButton(TagsType.GUATEMALA));
        buttons.put(39, new TagsButton(TagsType.CANADA));
        buttons.put(40, new TagsButton(TagsType.RUSSIA));

        return buttons;
    }

    private static class NormalTagCategoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short) 3)
                    .setName("&aNormal")
                    .setLore("&eClick to view category tags.")
                    .setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjgzN2YzZGIxM2E0MGQ0OTc5ZGU3NzE3OWUxOGFmNmUwYmMzY2MzOWVhNmFiYTUxOGJiMDgwYTZmMDFhNDAifX19").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            new NormalTagsMenu().openMenu(player);
        }
    }

    private static class CountriesTagCategoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short) 3)
                    .setName("&aCountries")
                    .setLore("&aYou are viewing this category!")
                    .setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc5ZTU0Y2JlODc4NjdkMTRiMmZiZGYzZjE4NzA4OTQzNTIwNDhkZmVjZDk2Mjg0NmRlYTg5M2IyMTU0Yzg1In19fQ==").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }

    private static class SpecialsTagCategoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.NETHER_STAR)
                    .setName("&aSpecials")
                    .setLore("&eClick to view category tags.").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }

    private static class PartnerTagCategoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.GOLD_INGOT)
                    .setName("&aPartner")
                    .setLore("&eClick to view category tags.").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }

    private static class CustomizableTagCategoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.BOOK_AND_QUILL)
                    .setName("&aCustomizable")
                    .setLore("&eClick to view category tags.").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }
}
