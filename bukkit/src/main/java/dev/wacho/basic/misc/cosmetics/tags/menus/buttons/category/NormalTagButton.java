package dev.wacho.basic.misc.cosmetics.tags.menus.buttons.category;

import com.cryptomorin.xseries.XMaterial;
import dev.wacho.basic.misc.cosmetics.tags.menus.NormalTagsMenu;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class NormalTagButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short) 3)
                .setName("&aNormal")
                .setLore("&aYou are viewing this category!")
                .setSkullOwner("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjgzN2YzZGIxM2E0MGQ0OTc5ZGU3NzE3OWUxOGFmNmUwYmMzY2MzOWVhNmFiYTUxOGJiMDgwYTZmMDFhNDAifX19").toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        new NormalTagsMenu().openMenu(player);
    }
}

