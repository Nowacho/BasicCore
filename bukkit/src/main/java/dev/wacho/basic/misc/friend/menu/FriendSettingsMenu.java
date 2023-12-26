package dev.wacho.basic.misc.friend.menu;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static dev.wacho.basic.utils.CC.translate;

public class FriendSettingsMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return translate("&6Friend Settings");
    }

    @Override
    public int size(Player player) {
        return 18;
    }

    @Override
    public Map<Integer, Button> getButtons(Player var1) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(3, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

                ItemBuilder builder = new ItemBuilder(Material.STONE_BUTTON);

                builder.setName("&6Toggle Click To Friend");

                if (playerData.getOptions().isFriendRightClickEnabled()) {
                    builder.setLore("&fClick to toggle on/off when you right",
                            "&fclick a player it sends them a friend",
                            "&frequest.",
                            " ",
                            "&fStatus: &aEnabled");
                } else {
                    builder.setLore(
                            "&fClick to toggle on/off when you right",
                            "&fclick a player it sends them a friend",
                            "&frequest.",
                            " ",
                            "&fStatus: &cDisabled");
                }

                return builder.toItemStack();
            }


            @Override
            public void clicked(Player player, ClickType clickType) {
                PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());
                playerData.getOptions().setFriendRightClickEnabled(!playerData.getOptions().isFriendRightClickEnabled());
                playerData.saveData();
            }

        });

        buttons.put(5, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {

                ItemBuilder builder = new ItemBuilder(Material.NETHER_STAR);

                builder.name(CC.translate("&6Toggle Friend Requests"));

                if (profile.getOptions().isFriendRequestsEnabled()) {
                    builder.lore(CC.translate(Arrays.asList(
                            " ",
                            "&fClick to toggle on/off if you can receive",
                            "&ffriend requests.",
                            " ",
                            "&fStatus: &aEnabled",
                            " "
                    )));
                } else {
                    builder.lore(CC.translate(Arrays.asList(
                            " ",
                            "&fClick to toggle on/off if you can receive",
                            "&ffriend requests.",
                            " ",
                            "&fStatus: &cDisabled",
                            " "
                    )));
                }

                return builder.build();
            }

            @Override
            public void clicked(Player player, ClickType clickType) {
                if (profile.getOptions().isFriendRequestsEnabled()) {
                    profile.getOptions().setFriendRequestsEnabled(false);
                } else {
                    profile.getOptions().setFriendRequestsEnabled(true);
                }
                profile.save();
            }
        });

        buttons.put(17, new BackButton(new ViewFriendsMenu(profile)));

        return buttons;
    }

    @Override
    public boolean isPlaceholder() {
        return true;
    }

    @Override
    public boolean isAutoUpdate() {
        return true;
    }
}
