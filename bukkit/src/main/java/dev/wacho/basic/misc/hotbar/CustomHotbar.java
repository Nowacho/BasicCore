package dev.wacho.basic.misc.hotbar;

import com.cryptomorin.xseries.XMaterial;
import dev.wacho.basic.utils.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
@Setter
public class CustomHotbar {

    private ItemStack item;
    private String skullOwner;
    private List<String> commands;
    private int itemSlot;
    private String name;
    private boolean enabled;

    public ItemStack getItem(Player player) {
        ItemStack itemStack = this.item.clone();

        if (this.skullOwner.equals("%player%")) {
            itemStack = new ItemBuilder(itemStack).setSkullOwner(player.getName()).toItemStack();
        }

        return itemStack;
    }

    public boolean isSimilar(ItemStack toCheck) {
        return (toCheck != null)
                && (toCheck.getType() != XMaterial.AIR.parseMaterial())
                && (toCheck.hasItemMeta())
                && (toCheck.getItemMeta().getDisplayName() != null)
                && toCheck.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName());
    }
}