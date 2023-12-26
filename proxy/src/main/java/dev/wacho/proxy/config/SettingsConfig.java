package dev.wacho.proxy.config;

import dev.wacho.proxy.util.config.ConfigCreator;
import dev.wacho.proxy.util.config.annotation.Path;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

public class SettingsConfig extends ConfigCreator {

    @Path(id = "COMMAND-BLOCKED")
    public static List<String> COMMAND_BLOCKED = Arrays.asList(
            "",
            "",
            ""
    );

    public SettingsConfig(Plugin plugin) {
        super(plugin, "settings.yml");
    }
}
