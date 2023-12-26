package dev.wacho.proxy.player;

import lombok.Getter;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager implements Listener {

    @Getter
    private static PlayerDataManager instance;

    @Getter
    private HashMap<UUID, PlayerData> playerDataStorage;

    public PlayerDataManager() {
        PlayerDataManager.instance = this;

        this.playerDataStorage = new HashMap<UUID, PlayerData>();

    }

    public void startplayerData(UUID uuid) {
        if (!this.playerDataStorage.containsKey(uuid)) {
            this.playerDataStorage.put(uuid, new PlayerData());
        }
    }

    public PlayerData getplayerData(UUID uuid) {
        if (this.playerDataStorage.containsKey(uuid)) {
            return this.playerDataStorage.get(uuid);
        }
        return null;
    }

    @EventHandler
    public void onJoin(ServerConnectedEvent event) {
        this.startplayerData(event.getPlayer().getUniqueId());
    }

    public void setplayerDataStorage(HashMap<UUID, PlayerData> playerDataStorage) {
        this.playerDataStorage = playerDataStorage;
    }

}
