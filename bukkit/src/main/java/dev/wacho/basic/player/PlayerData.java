package dev.wacho.basic.player;

import com.google.common.collect.Maps;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.UpdateOptions;
import dev.wacho.basic.Basic;
import dev.wacho.basic.database.mongo.MongoManager;
import dev.wacho.basic.misc.cosmetics.color.chatcolor.ChatColor;
import dev.wacho.basic.misc.cosmetics.color.namecolor.MessageColor;
import dev.wacho.basic.misc.cosmetics.death.deathmessage.DeathMessage;
import dev.wacho.basic.misc.cosmetics.death.killeffect.impl.KillEffects;
import dev.wacho.basic.misc.cosmetics.effects.Effects;
import dev.wacho.basic.misc.cosmetics.joinmessage.JoinMessage;
import dev.wacho.basic.misc.cosmetics.projectiles.Projectiles;
import dev.wacho.basic.misc.cosmetics.tags.TagsType;
import dev.wacho.basic.misc.tag.Tag;
import dev.wacho.basic.utils.Utils;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.activated.core.plugin.AquaCore;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class PlayerData {

    @Getter
    private static Map<UUID, PlayerData> playerData = Maps.newHashMap();

    // Player identification
    private String name;
    private UUID uuid;
    private boolean dataLoaded;

    // Others things
    private String lastServer;
    private long lastServerTime;
    private String country;

    // Coins System
    private int coins;

    // Name MC
    private boolean vote;

    // Chat Stuff
    @Setter private Tag tag;

    //private String tag;
    private String nameColor;
    private String chatColor;
    private boolean bold;
    private boolean italic;

    // Chat Cosmetics
    private TagsType tagsType;
    //private ChatColor chatColor;
    private MessageColor messageColor;
    private DeathMessage deathMessage;
    private JoinMessage joinMessage;

    // Particles Cosmetics
    private KillEffects killEffects;
    private Effects effects;
    private Projectiles projectiles;

    public PlayerData(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
        this.dataLoaded = false;
        loadData();
    }

    public PlayerData(String name, UUID uuid, boolean msg) {
        this.name = name;
        this.uuid = uuid;
        this.dataLoaded = false;
        loadData(msg);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    public boolean isOnline() {
        return (Bukkit.getPlayer(this.uuid) != null);
    }

    public void saveData() {
        Document document = new Document();
        document.put("name", this.name);
        if (getPlayer() != null) {
            document.put("name_lowercase", getPlayer().getName().toLowerCase());
        } else {
            document.put("name_lowercase", this.name.toLowerCase());
        }
        document.put("uuid", getUuid().toString());

        document.put("last-server-time", this.lastServerTime);

        if (getPlayer() != null) {
            try {
                document.put("country", Utils.getCountry(getPlayer().getAddress().getAddress().toString().replaceAll("/", "")));
            } catch (Exception e) {
                Bukkit.getLogger().info("Error in get player country");
            }
        } else {
            document.put("country", this.country);
        }

        document.put("coins", this.coins);
        document.put("name-mc-vote", this.vote);

        MongoManager mongoManager = Basic.getInst().getMongoManager();
        mongoManager.getPlayerCollection().replaceOne(Filters.eq("uuid", this.uuid.toString()), document, (new UpdateOptions()).upsert(true));
    }

    public void loadData(boolean message) {
        MongoManager mongoManager = Basic.getInst().getMongoManager();
        Document document = mongoManager.getPlayerCollection().find(Filters.eq("uuid", this.uuid.toString())).first();
        if (document != null) {
            try {
                this.lastServer = document.getString("last-server");
                this.lastServerTime = document.getLong("last-server-time");
                this.country = document.getString("country");

                // Check for null before accessing values
                this.coins = document.getInteger("coins", 0);
                this.vote = document.getBoolean("name-mc-vote");

                // Check for null before accessing values and validate enum constants
                /*this.tagsType = document.getString("tags") != null ? TagsType.valueOf(document.getString("tags")) : null;
                this.projectiles = document.getString("projectiles") != null ? Projectiles.valueOf(document.getString("projectiles")) : null;*/

                this.dataLoaded = true;
                if (message) {
                    Basic.getInst().getLogger().info(this.getName() + "'s data was successfully loaded.");
                }
            } catch (Exception e) {
                // Log the exception for debugging
                Basic.getInst().getLogger().severe("Error loading player data: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void loadData() {
        MongoManager mongoManager = Basic.getInst().getMongoManager();
        Document document = mongoManager.getPlayerCollection().find(Filters.eq("uuid", this.uuid.toString())).first();
        if (document != null) {

            this.lastServer = document.getString("last-server");
            if (document.containsKey("last-server-time")) {
                this.lastServerTime = document.getLong("last-server-time");
            }

            this.country = document.getString("country");
            this.coins = document.getInteger("coins");
            this.vote = document.getBoolean("name-mc-vote");
            //Integer coinsFromDocument = document.getInteger("coins");
            //this.coins = (coinsFromDocument != null) ? coinsFromDocument : 0;
        }
        this.dataLoaded = true;
        Basic.getInst().getLogger().info(PlayerData.this.getName() + "'s data was successfully loaded.");
    }

    public void removeData() {
        this.saveData();
        playerData.remove(this.uuid);
    }

    // Data handler
    public static PlayerData createPlayerData(UUID uuid, String name) {
        if (playerData.containsKey(uuid)) return getPlayerData(uuid);
        playerData.put(uuid, new PlayerData(name, uuid));
        return getPlayerData(uuid);
    }

    public static PlayerData getPlayerData(UUID uuid) {
        return playerData.get(uuid);
    }

    public static PlayerData getPlayerData(Player player) {
        return playerData.get(player.getUniqueId());
    }

    public static PlayerData getPlayerData(String name) {
        Document document = Basic.getInst().getMongoManager().getPlayerCollection().find(Filters.eq("name", name)).first();
        if (document == null) {
            return null;
        }

        return playerData.get(UUID.fromString(document.getString("uuid")));
    }

    public static void deleteOfflineProfile(PlayerData profile) {
        if (Bukkit.getPlayer(profile.getUuid()) == null) {
            profile.saveData();
            playerData.remove(profile.getUuid());
        }
    }

    public static void deleteOfflineProfile(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null) {
            return;
        }
        playerData.get(uuid).saveData();
        playerData.remove(uuid);
    }

    public static boolean hasData(UUID uuid) {
        Document document = Basic.getInst().getMongoManager().getPlayerCollection().find(Filters.eq("uuid", uuid.toString())).first();

        return document != null;
    }

    public static boolean hasData(String name) {
        Document document = Basic.getInst().getMongoManager().getPlayerCollection().find(Filters.eq("name", name)).first();

        return document != null;
    }

    public static PlayerData loadData(UUID uuid) {
        Document document = Basic.getInst().getMongoManager().getPlayerCollection().find(Filters.eq("uuid", uuid.toString())).first();
        if (document == null) {
            return null;
        }
        createPlayerData(uuid, document.getString("name"));

        return getPlayerData(uuid);
    }

    public static void startTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                playerData.values().forEach(playerData -> {
                    if (playerData.getPlayer() == null) {
                        playerData.removeData();
                    }
                });
            }
        }.runTaskTimer(Basic.getInst(), 20L, TimeUnit.MINUTES.toMillis(5L));
    }
}
