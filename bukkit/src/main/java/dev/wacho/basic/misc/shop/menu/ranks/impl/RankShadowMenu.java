package dev.wacho.basic.misc.shop.menu.ranks.impl;

import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import dev.wacho.basic.utils.menu.button.BackButton;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static dev.wacho.basic.utils.CC.translate;

public class RankShadowMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8Rank Shadow ┃ 1/1");
    }

    @Override
    public int size(Player player) {
        return 9 * 3;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(0, new BackButton(this));

        return buttons;
    }


    @AllArgsConstructor
    private static class SoupInfoButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.MUSHROOM_SOUP)
                    .setName("&aSoupPvP")
                    .setLore("&7- Access to two kits",
                            "&7- Access to the &f/reclaim &7command",
                            "&7- Access to do &dSumo &7events").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }

    @AllArgsConstructor
    private static class SurvivalInfoButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.DIAMOND_PICKAXE)
                    .setName("&aSurvival")
                    .setLore("&7- Access to the &f/kit shadow &7command",
                            "&7- Access to the &f/feed &7command",
                            "&7- Access to the &f/ec &7command",
                            "&7- Access to the &f/reclaim &7command",
                            "&7- Access to having an aria of 50 chunks",
                            "&7- Access to 3 homes").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }

    @AllArgsConstructor
    private static class GlobalInfoButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.NETHER_STAR)
                    .setName("&aGlobal")
                    .setLore("&7Shadow provides standard features",
                            "&7for bypassing restrictions",
                            "",
                            "&7- Displayed as: &7[&f&l☽&8Shadow&7] &8" + player.getName(),
                            "&7- &8Dark Gray &7Name Color",
                            "&7- Chat delay restrictions bypass",
                            "&7- Access to &dVIP &7chat using &f/vip",
                            "&7and much more...").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }
}
