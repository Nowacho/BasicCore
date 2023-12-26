package dev.wacho.basic;

import dev.wacho.basic.commands.CommandHandler;
import dev.wacho.basic.database.mongo.MongoManager;
import dev.wacho.basic.handler.*;
import dev.wacho.basic.handler.hotbar.CustomHotbarHandler;
import dev.wacho.basic.integrations.PlaceholderAPIHook;
import dev.wacho.basic.misc.cosmetics.CosmeticHandler;
import dev.wacho.basic.misc.hotbar.CustomHotbarManager;
import dev.wacho.basic.misc.tag.TagManager;
import dev.wacho.basic.misc.timer.TimerManager;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.player.PlayerDataLoad;
import dev.wacho.basic.board.scoreboard.Adapter;
import dev.wacho.basic.board.scoreboard.Assemble;
import dev.wacho.basic.board.scoreboard.AssembleStyle;
import dev.wacho.basic.board.tablist.Tablist;
import dev.wacho.basic.board.tablist.TablistProvider;
import dev.wacho.basic.board.tablist.head.HeadManager;
import dev.wacho.basic.utils.*;
import dev.wacho.basic.utils.menu.MenuListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter @Setter
public final class Basic extends JavaPlugin {

    @Getter private static Basic inst;

    private FileConfig configFile, messageFile, scoreboardFile, tabFile, tabHeadFile, hotbarFile, tagsFile;

    private MongoManager mongoManager;
    private TimerManager timerManager;
    private HeadManager headManager;
    private Tablist tablist;
    private CustomHotbarManager customHotbarManager;
    private TagManager tagManager;
    // Idk
    private String disableMessage = "null";
    private boolean joined = false;

    @Override
    public void onEnable() {
        inst = this;

        this.registerConfigs();
        this.registerListeners();
        this.registerManager();

        new CommandHandler();

        CC.log(CC.CHAT_BAR);
        CC.log("&bBasic &8- &fv" + getDescription().getVersion());
        CC.log("&7Developed on &3NoWacho");
        CC.log(" ");
        CC.log("&7 • &fDevelopers: &c" + this.getDescription().getAuthors());
        CC.log("&7 • &fMongo: " + (mongoManager.isConnected() ? "&aenabled" : "&cdisabled"));
        //CC.log("&7 • &fRedis: " + (redisManager.isActive() ? "&aenabled" : "&cdisabled"));
        CC.log(CC.CHAT_BAR);
    }

    private void registerConfigs() {
        try {
            this.configFile = new FileConfig(this, "settings.yml");
            this.scoreboardFile = new FileConfig(this, "scoreboard.yml");
            this.tabFile = new FileConfig(this, "tablist/tab.yml");
            this.tabHeadFile = new FileConfig(this, "tablist/heads.yml");
            this.hotbarFile = new FileConfig(this, "hotbar.yml");
        } catch (RuntimeException e) {
            CC.log("&cYMLs did not load, please restart the server or contact support.");
            Bukkit.getPluginManager().disablePlugin(this);
            Bukkit.getScheduler().cancelTasks(this);
        }
    }

    private void registerListeners() {
        PluginManager manager = this.getServer().getPluginManager();
        //CC.log("&4[Basic] &7Loading Listeners...");
        manager.registerEvents(new PlayerDataLoad(), this);
        manager.registerEvents(new PunishmentsHandler(), this);
        manager.registerEvents(new ChatHandler(), this);
        manager.registerEvents(new PlayerHandler(), this);
        manager.registerEvents(new CustomHotbarHandler(), this);
        manager.registerEvents(new LaunchPadHandler(), this);
        manager.registerEvents(new CosmeticHandler(), this);
        manager.registerEvents(new MenuListener(this), this);
        //CC.log("&4[Basic] &7Loaded Listeners.");
    }

    @Override
    public void onDisable() {
        if (tablist != null) tablist.onDisable();

        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());
            if (playerData != null) {
                playerData.saveData();
            }
        }

        mongoManager.disconnect();
    }

    private void registerManager() {
        //CC.log("&4[Basic] &7Loading Manager...");
        this.mongoManager = new MongoManager();
        this.timerManager = new TimerManager();
        this.headManager = new HeadManager(this);
        this.customHotbarManager = new CustomHotbarManager(this);

        this.mongoManager.connect();
        if (!this.mongoManager.isConnected()) {
            return;
        }

        PlaceholderAPIHook.initialize(this);

        Animation.init();
        ScoreboardAnimated.init();
        if (configFile.getBoolean("BOARD-ENABLED")) {
            Assemble assemble = new Assemble(this, new Adapter());
            assemble.setTicks(2);
            assemble.setAssembleStyle(AssembleStyle.MODERN);
        }

        if (configFile.getBoolean("TAB-ENABLED")) {
            tablist = new Tablist(this, new TablistProvider(this));
        }

        PlayerData.startTask();
        TaskUtil.runLaterAsync(() -> setJoined(true), 5 * 20);
        //CC.log("&4[Basic] &7Loaded Manager.");
    }
}
