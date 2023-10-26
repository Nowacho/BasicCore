package dev.wacho.basic.tablist.interfaces;

import dev.wacho.basic.tablist.impl.IETablist;
import dev.wacho.basic.tablist.impl.utils.SkinTexture;
import dev.wacho.basic.tablist.impl.utils.TabColumn;
import dev.wacho.basic.tablist.impl.utils.TabEntry;
import org.bukkit.entity.Player;

public interface IEHelper {

    TabEntry createEntry(IETablist tablist, String name, TabColumn column, int slot, int rawSlot, boolean legacy);

    void updateText(IETablist tablist, TabEntry entry, String[] newStrings);

    void updateTexture(IETablist tablist, TabEntry entry, SkinTexture skinTexture);

    void updatePing(IETablist tablist, TabEntry entry, int ping);

    void updateHeaderAndFooter(Player player, String header, String footer);

    SkinTexture getTexture(Player player);

}
