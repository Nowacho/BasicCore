package dev.wacho.basic.misc.cosmetics.tags.menus.buttons;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.player.PlayerDataManager;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class TagsRemoveButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.FLINT_AND_STEEL)
                .setName("&cDeactivate tag").toItemStack();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        PlayerData playerData = PlayerDataManager.getPlayerData(player.getUniqueId());

        if (playerData.getTagsType() == null) {
            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
            return;
        }

        playerData.setTagsType(null);
    }
}
