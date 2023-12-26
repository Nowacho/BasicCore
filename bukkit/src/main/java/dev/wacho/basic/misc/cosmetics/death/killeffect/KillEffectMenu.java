package dev.wacho.basic.misc.cosmetics.death.killeffect;

import dev.wacho.basic.Basic;
import dev.wacho.basic.misc.cosmetics.death.killeffect.impl.KillEffects;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.menu.Button;
import dev.wacho.basic.utils.menu.button.BackButton;
import dev.wacho.basic.utils.menu.button.CloseButton;
import dev.wacho.basic.utils.menu.pagination.PageButton;
import dev.wacho.basic.utils.menu.pagination.PaginatedMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class KillEffectMenu extends PaginatedMenu {

    private final PlayerData data;

    @Override
    public String getPrePaginatedTitle(Player player) {
        return CC.translate("Death Animations");
    }

    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new PageButton(-1, this));
        buttons.put(8, new PageButton(1, this));

        buttons.put(35, new CloseButton());

        buttons.put(3, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("Available&7: " + KillEffects.values().length);
                lore.add("");
                lore.add("Click one of the effects");
                lore.add("to equip it on profile.");
                return new ItemBuilder(Material.NETHER_STAR).setLore(lore).setName("Information").toItemStack();
            }
        });

        buttons.put(5, new Button() {
            @Override
            public ItemStack getButtonItem(Player player) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("Click here to remove");
                lore.add("current effect from profile..");
                return new ItemBuilder(Material.BLAZE_POWDER).setLore(lore).setName("Reset").toItemStack();
            }

            @Override
            public void clicked(Player player, ClickType clickType) {
                data.setKillEffects(null);
                player.sendMessage("You effect has been removed.");
            }
        });

        return buttons;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        Stream.of(KillEffects.values()).forEach(killEffects -> {
            buttons.put(buttons.size(), new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    ItemBuilder item = new ItemBuilder(Material.WOOL).setDurability((short) 10);
                    List<String> lore = new ArrayList<>();
                    lore.add("");
                    lore.add("Middle-Click to preview this effect.");
                    lore.add("Right-Click here to equip this effect.");

                    return item.setName(killEffects.getName()).setLore(lore).toItemStack();
                }

                @Override
                public void clicked(Player player, ClickType clickType) {
                    if (clickType == ClickType.MIDDLE) {
                        killEffects.getCallable().call(player.getLocation().clone().add(0.0D, 1.0D, 0.0D));
                    } else {
                        if (player.hasPermission("cosmetic.idk")) {
                            data.setKillEffects(killEffects);
                            player.sendMessage(data.getKillEffects().getName() + " is now set as your killeffect.");
                            player.closeInventory();
                        } else {
                            player.sendMessage("You don't own any effect. Buy it at our store " + Basic.getInst().getConfig().getString("SERVER_STORE"));
                        }
                    }
                }
            });
        });

        return buttons;
    }

    @Override
    public int size(Player player) {
        return 9 * 4;
    }
}
