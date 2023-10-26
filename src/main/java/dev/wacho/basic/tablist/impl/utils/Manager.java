package dev.wacho.basic.tablist.impl.utils;

import org.bukkit.plugin.java.JavaPlugin;

public interface Manager {

    default void onEnable(JavaPlugin plugin) {
    }

    default void onDisable(JavaPlugin plugin) {
    }
}