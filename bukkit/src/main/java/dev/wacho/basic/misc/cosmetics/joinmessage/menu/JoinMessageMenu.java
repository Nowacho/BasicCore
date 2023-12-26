package dev.wacho.basic.misc.cosmetics.joinmessage.menu;

import dev.wacho.basic.misc.cosmetics.death.deathmessage.menu.DeathMessageMenu;
import dev.wacho.basic.misc.cosmetics.joinmessage.JoinMessage;
import dev.wacho.basic.misc.cosmetics.joinmessage.menu.buttons.JoinButton;
import dev.wacho.basic.misc.cosmetics.utils.all.SeparatorButton;
import dev.wacho.basic.misc.cosmetics.utils.all.SeparatorCategoryButton;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import dev.wacho.basic.utils.menu.button.CloseButton;
import dev.wacho.basic.utils.menu.pagination.PageButton;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static dev.wacho.basic.utils.CC.translate;

public class JoinMessageMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&8Join Messages ┃ 1/1");
    }

    @Override
    public int size(Player player) {
        return 9 * 6;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        for (int i = 9; i <= 17; i++) {
            buttons.put(i, new SeparatorButton());
        }

        buttons.put(11, new SeparatorCategoryButton());
        buttons.put(0, new CloseButton());

        buttons.put(3, new DeathMessageCategoryButton());
        buttons.put(4, new JoinMessageCategoryButton());

        buttons.put(19, new JoinButton(JoinMessage.SERVER));
        buttons.put(20, new JoinButton(JoinMessage.DEFAULT));
        buttons.put(21, new JoinButton(JoinMessage.ROBLOX));
        buttons.put(22, new JoinButton(JoinMessage.PARTY));
        buttons.put(23, new JoinButton(JoinMessage.OP));
        buttons.put(24, new JoinButton(JoinMessage.VELNOX));
        //buttons.put(25, new JoinButton(JoinMessage.CHEATING));


        return buttons;
    }


    private static class DeathMessageCategoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.NAME_TAG)
                    .setName("&aDeath Message")
                    .setLore("&7Equip an awesome death", "&7message that will be triggered", "&7every time you die!", "", "&aYou are viewing this category!").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            new DeathMessageMenu().openMenu(player);
        }
    }

    private static class JoinMessageCategoryButton extends Button {

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.SIGN)
                    .setName("&aJoin Message")
                    .setLore("&7Equip a message that announces", "&7your entrance to any of our", "&7gamemodes with style!", "", "&eView category items!").toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
        }
    }
}
