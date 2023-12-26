package dev.wacho.basic.misc.cosmetics.tags.menus.buttons;

import dev.wacho.basic.misc.cosmetics.tags.TagsType;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.menu.Button;
import lombok.RequiredArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class TagsButton extends Button {

    private TagsType tagsType;

    public TagsButton(TagsType tagsType) {
        this.tagsType = tagsType;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return tagsType.getIcon(player, PlayerData.getPlayerData(player.getUniqueId()));
    }
    @Override
    public void clicked(Player player, ClickType clickType) {
        if (player.hasPermission(tagsType.getPermission())) {
            PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

            if (playerData.getTagsType() != null && playerData.getTagsType().equals(tagsType)) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
                return;
            }

            playerData.setTagsType(tagsType);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 40F, 30L);
        } else if (player.hasPermission("basic.tags.*")) {
            PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

            if (playerData.getTagsType() != null && playerData.getTagsType().equals(tagsType)) {
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
                return;
            }

            playerData.setTagsType(tagsType);
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 40F, 30L);
        } else {
            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 3500L, 3500F);
        }
    }
}
