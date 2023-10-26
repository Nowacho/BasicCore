package dev.wacho.basic.tablist.interfaces;

import dev.wacho.basic.tablist.impl.utils.TabLayout;
import org.bukkit.entity.Player;

import java.util.Set;

public interface IEAdapter {

    Set<TabLayout> getSlots(Player player);

    String getFooter(Player player);

    String getHeader(Player player);

}
