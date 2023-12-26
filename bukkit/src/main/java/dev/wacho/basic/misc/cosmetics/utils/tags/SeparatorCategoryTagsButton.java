package dev.wacho.basic.misc.cosmetics.utils.tags;

import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SeparatorCategoryTagsButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.STAINED_GLASS_PANE)
                .setName("&aYou are viewing this category!").setDurability((short) 5).toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        //player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
    }
}
