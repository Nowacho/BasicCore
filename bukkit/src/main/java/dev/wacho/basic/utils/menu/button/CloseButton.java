package dev.wacho.basic.utils.menu.button;

import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class CloseButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.BED)
                .setName("&cClose").toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        player.closeInventory();
    }
}
