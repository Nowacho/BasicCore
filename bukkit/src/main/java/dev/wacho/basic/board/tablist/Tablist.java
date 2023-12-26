package dev.wacho.basic.board.tablist;

import dev.wacho.basic.Basic;
import dev.wacho.basic.board.tablist.player.PlayerTablist;
import dev.wacho.basic.board.tablist.player.PlayerVersionManager;
import dev.wacho.basic.board.tablist.version.TablistVersion;
import dev.wacho.basic.board.tablist.version.impl.ProtocolTablist;
import dev.wacho.basic.utils.CC;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class Tablist {

    @Getter
    private static Tablist instance;

    private final Basic plugin;
    private final TablistAdapter tablistAdapter;
    private final Map<UUID, PlayerTablist> playerTablist;
    private TablistThread tablistThread;
    private TablistVersion tablistVersion;
    private final PlayerVersionManager playerVersionManager;

    public Tablist(@NonNull Basic plugin, TablistAdapter adapter) {
        instance = this;

        this.plugin = plugin;
        this.tablistAdapter = adapter;
        this.playerTablist = new ConcurrentHashMap<>();
        this.playerVersionManager = new PlayerVersionManager();

        this.setupTablistVersion();
        this.setup();
    }

    private void setupTablistVersion() {
        if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
            this.tablistVersion = new ProtocolTablist(this.plugin);
        } else {
            CC.log("&c[Tablist] ProtocolLib not found, disabling Tablist");
        }
    }

    private void setup() {
        if (this.tablistVersion == null) return;

        this.plugin.getServer().getPluginManager().registerEvents(new TablistListener(this.plugin), this.plugin);

        if (this.tablistThread != null) {
            this.tablistThread.stop();
            this.tablistThread = null;
        }

        this.tablistThread = new TablistThread(this);
    }

    public void onDisable() {
        if (this.tablistThread != null) {
            this.tablistThread.stop();
            this.tablistThread = null;
        }

        for (UUID uuid : getPlayerTablist().keySet()) {
            Player player = Bukkit.getPlayer(uuid);

            if (player == null || !player.isOnline()) continue;

            getPlayerTablist().remove(uuid);
        }
    }
}
