package dev.wacho.basic;

import dev.wacho.basic.commands.CommandHandler;
import dev.wacho.basic.integrations.PlaceholderAPIHook;
import dev.wacho.basic.misc.lunarclient.LunarClientRunnable;
import dev.wacho.basic.scoreboard.Adapter;
import dev.wacho.basic.scoreboard.Assemble;
import dev.wacho.basic.scoreboard.AssembleStyle;
import dev.wacho.basic.tablist.TabAdapter;
import dev.wacho.basic.tablist.impl.TablistManager;
import dev.wacho.basic.utils.Animation;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.FileConfig;
import dev.wacho.basic.utils.ScoreboardAnimated;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Basic extends JavaPlugin {

    private FileConfig configFile, scoreboardFile, tabFile;

    @Override
    public void onEnable() {
        this.registerConfigs();
        this.registerManager();
    }

    private void registerConfigs() {
        try {
            this.configFile = new FileConfig(this, "settings.yml");
            this.scoreboardFile = new FileConfig(this, "scoreboard.yml");
            this.tabFile = new FileConfig(this, "tab.yml");
        } catch (RuntimeException e) {
            CC.log("&cYMLs did not load, please restart the server or contact support.");
            Bukkit.getPluginManager().disablePlugin(this);
            Bukkit.getScheduler().cancelTasks(this);
        }
    }

    @Override
    public void onDisable() {

    }

    private void registerManager() {
        Bukkit.getConsoleSender().sendMessage(CC.translate("&4[Basic] &7Loading Manager..."));

        new CommandHandler();

        PlaceholderAPIHook.initialize(this);
        Animation.init();
        ScoreboardAnimated.init();

        if (Basic.getInst().getConfigFile().getBoolean("BOARD-ENABLED")) {
            Assemble assemble = new Assemble(this, new Adapter());
            assemble.setTicks(2);
            assemble.setAssembleStyle(AssembleStyle.KOHI);
        }

        if (Basic.getInst().getConfigFile().getBoolean("TAB-ENABLED")) {
            TablistManager.getInst.onEnable(this);
            TablistManager.getInst.setAdapter(new TabAdapter());
        }

        if (Basic.getInst().getConfigFile().getBoolean("LUNAR-ENABLED")) {
            Bukkit.getScheduler().runTaskTimer(this, new LunarClientRunnable(this), 0L, 20L);
        }
        Bukkit.getConsoleSender().sendMessage(CC.translate("&4[Basic] &7Loaded Manager."));
    }

    public static Basic getInst() {
        return Basic.getPlugin(Basic.class);
    }
}
