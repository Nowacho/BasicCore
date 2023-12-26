package dev.wacho.basic.integrations;

import dev.wacho.basic.Basic;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

@UtilityClass
public class PlaceholderAPIHook {

    @Getter
    private boolean enabled;

    public void initialize(Basic plugin) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            PlaceholderAPIExpansion papi = new PlaceholderAPIExpansion(plugin);
            if (!papi.isRegistered()) papi.register();
            enabled = true;
        }
    }
}
