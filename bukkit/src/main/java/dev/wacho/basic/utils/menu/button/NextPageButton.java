package dev.wacho.basic.utils.menu.button;

import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class NextPageButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.ARROW)
                .setName("&aNext page").toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {

    }
}
