package dev.wacho.basic.shop.rank.impl;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.ItemBuilder;
import dev.wacho.basic.utils.Utils;
import dev.wacho.basic.utils.menu.Button;
import lombok.AllArgsConstructor;
import me.activated.core.api.rank.grant.Grant;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DecoyButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.LEATHER_CHESTPLATE)
                .setRGBColor(173, 96, 182)
                .setName("&dDecoy Rank")
                .setLore(getsetLore(player)).toItemStack();
    }

    public List<String> getsetLore(Player player) {
        List<String> list = new ArrayList<>();
        list.add("&7Decoy provides standard features");
        list.add("&7for bypassing restrictions");
        list.add("");
        list.add("&7- Displayed as: &7[&f✩&dDecoy&7] &d" + player.getName());
        list.add("&7- &dLight Purple &7Name Color");
        list.add("&7- Chat delay restrictions bypass");
        list.add("&7- Access to &dVIP &7chat using &f/vip");
        list.add("&7- Join gamemodes directly by typing");
        list.add("&7their name, for example, &f/soup");
        list.add("&7and much more...");
        list.add("");
        return list;
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());
        if (playerData.getCoins() < 200) {
            //player.sendMessage(CC.translate("&cYou don't have enough coins to buy this rank."));
            return;
        }

        Grant grant = new Grant();
        grant.setRankName("Decoy");
        grant.setReason("Purchased rank with Coins");
        grant.setPermanent(true);
        grant.setAddedBy("Console");
        grant.setAddedAt(System.currentTimeMillis());
        grant.setActive(true);

        // You may want to handle the broadcast logic as needed
        Utils.globalBroadcast(player, CC.translate(player.getDisplayName() + " &fin our in-game store! If you want to support the server, please consider visiting &c&ntienda.velnox.club&f!"));

        // Update player's data
        playerData.setCoins(playerData.getCoins() - 200);
        playerData.saveData();
    }
}
