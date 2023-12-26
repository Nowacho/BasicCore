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

import java.util.HashMap;
import java.util.Map;

import static dev.wacho.basic.utils.CC.translate;

public class RankDecoyMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8Rank Decoy ┃ 1/1");
    }

    @Override
    public int size(Player player) {
        return 9 * 3;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        buttons.put(0, new BackButton(this));

        buttons.put(11, new SoupInfoButton());
        buttons.put(13, new GlobalInfoButton());
        buttons.put(15, new SurvivalInfoButton());
        setAutoUpdate(true);

        return buttons;
    }


    @AllArgsConstructor
    private static class SoupInfoButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.MUSHROOM_SOUP)
                    .setName("&aSoupPvP")
                    .setLore("&7- Access to five kits",
                            "&7- Access to the &f/reclaim &7command",
                            "&7- Access to do &dSumo&7, &aSpleef&7, &cBrackets &7events").toItemStack();
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
                            "&7- Access to the &f/fly &7command",
                            "&7- Access to the &f/feed &7command",
                            "&7- Access to the &f/ec &7command",
                            "&7- Access to the &f/reclaim &7command",
                            "&7- Access to having an aria of 70 chunks",
                            "&7- Access to 6 homes").toItemStack();
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
                    .setLore("&7Decoy provides standard features",
                            "&7for bypassing restrictions",
                            "",
                            "&7- Displayed as: &7[&f✩&dDecoy&7] &d" + player.getName(),
                            "&7- &dLight Purple &7Name Color",
                            "&7- Chat delay restrictions bypass",
                            "&7- Access to &dVIP &7chat using &f/vip",
                            "&7- Join gamemodes directly by typing",
                            "&7their name, for example, &f/soup",
                            "&7and much more...").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }
}
