package dev.wacho.basic.utils.menu.button;

import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.pagination.PaginatedMenu;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

@AllArgsConstructor
public class PageInfoButton extends Button {

    private PaginatedMenu paginatedMenu;

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.NETHER_STAR)
                .setName("&ePage Info")
                .setLore(Collections.singletonList("&e" + paginatedMenu.getPage() + "&7/&a" + paginatedMenu.getPages(player)))
                .toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {

    }
}