package dev.wacho.basic.misc.hotbar;

import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.FileConfig;
import dev.wacho.basic.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomHotbarManager {

    private final Basic plugin;
    private final Map<String, CustomHotbar> hotbarMap;
    private final Set<String> hotbarNames;

    public CustomHotbarManager(Basic bamboo) {
        this.plugin = bamboo;
        this.hotbarMap = new HashMap();
        this.hotbarNames = new HashSet();
        this.loadOrRefresh(false);
    }

    public Collection<CustomHotbar> getHotbars() {
        return hotbarMap.values();
    }

    public boolean exists(String name) {
        return hotbarMap.containsKey(name);
    }

    public CustomHotbar getHotbar(String name) {
        return hotbarMap.get(name);
    }

    public CustomHotbar getHotbar(ItemStack item) {
        for (CustomHotbar hotbar : getHotbars()) {
            if (hotbar.isSimilar(item)) {
                return hotbar;
            }
        }
        return null;
    }

    public void giveHotbar(Player player) {
        for (CustomHotbar hotbar : getHotbars()) {
            if (hotbar.isEnabled()) player.getInventory().setItem(hotbar.getItemSlot(), hotbar.getItem(player));
        }
    }

    public void loadOrCreate(String name, ConfigurationSection section) {
        CustomHotbar hotbar = exists(name) ? getHotbar(name) : new CustomHotbar();
        hotbar.setName(name);
        hotbar.setEnabled(section.getBoolean(name + ".ENABLED"));
        hotbar.setItem(new ItemBuilder(Material.valueOf(section.getString(name + ".ITEM.MATERIAL")))
                .setName(section.getString(name + ".ITEM.NAME"))
                .setLore(section.getStringList(name + ".ITEM.DESCRIPTION"))
                .setDurability((short) section.getInt(name + ".ITEM.DATA"))
                .setSkullOwner(section.getString(name + ".ITEM.HEAD")).toItemStack());
        hotbar.setItemSlot(section.getInt(name+ ".ITEM.SLOT"));
        hotbar.setSkullOwner(section.getString(name + ".ITEM.HEAD"));
        hotbar.setCommands(section.getStringList(name + ".COMMANDS"));

        if (!exists(name)) hotbarMap.put(name, hotbar);
    }

    public void loadOrRefresh(boolean reload) {
        FileConfig hotbarFile = plugin.getHotbarFile();
        ConfigurationSection section = hotbarFile.getConfiguration().getConfigurationSection("CUSTOM");

        for (String hotbarName : section.getKeys(false)) {
            loadOrCreate(hotbarName, section);
        }

        for (CustomHotbar hotbar : hotbarMap.values()) {
            String hotbarName = hotbar.getName();
            if (!section.contains(hotbarName)) hotbarNames.add(hotbarName);
        }

        hotbarNames.forEach(hotbarMap::remove);
        hotbarNames.clear();

        if (reload) Bukkit.getOnlinePlayers().forEach(this::loadOrRefresh);
    }

    private void loadOrRefresh(Player player) {
        this.giveHotbar(player);
    }
}