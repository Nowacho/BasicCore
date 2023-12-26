package dev.wacho.basic.database.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.command.BaseCommand;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.Collections;

@Getter
public class MongoManager {

    /*private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> playerCollection;

    public MongoManager() {
        try {
            if (plugin.getConfigFile().getConfiguration().getBoolean("MONGODB.AUTHENTICATION.ENABLED")) {
                final MongoCredential credential = MongoCredential.createCredential(
                        plugin.getConfigFile().getConfiguration().getString("MONGODB.AUTHENTICATION.USERNAME"),
                        plugin.getConfigFile().getConfiguration().getString("MONGODB.AUTHENTICATION.DATABASE"),
                        plugin.getConfigFile().getConfiguration().getString("MONGODB.AUTHENTICATION.PASSWORD").toCharArray()
                );
                mongoClient = new MongoClient(new ServerAddress(plugin.getConfigFile().getConfiguration().getString("MONGODB.ADDRESS"), plugin.getConfigFile().getConfiguration().getInt("MONGODB.PORT")), Collections.singletonList(credential));
            } else {
                mongoClient = new MongoClient(plugin.getConfigFile().getConfiguration().getString("MONGODB.ADDRESS"), plugin.getConfigFile().getConfiguration().getInt("MONGODB.PORT"));
            }
            mongoDatabase = mongoClient.getDatabase(plugin.getConfigFile().getConfiguration().getString("MONGODB.DATABASE"));
            mongoDatabase = mongoClient.getDatabase("Basic");
            playerCollection = mongoDatabase.getCollection("PlayerData");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(CC.translate("&4[Basic] &cFailed to connect to MongoDB"));
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        }
    }*/

    private final Basic plugin = Basic.getInst();
    private MongoClient client;
    private MongoDatabase mongoDatabase;

    private final String host = plugin.getConfigFile().getString("MONGO.HOST");
    private final int port = plugin.getConfigFile().getInt("MONGO.PORT");
    private final String database = plugin.getConfigFile().getString("MONGO.DATABASE");
    private final boolean authentication = plugin.getConfigFile().getBoolean("MONGO.AUTH.ENABLED");

    private final String user = plugin.getConfigFile().getString("MONGO.AUTH.USERNAME");
    private final String password = plugin.getConfigFile().getString("MONGO.AUTH.PASSWORD");
    private final String authDatabase = plugin.getConfigFile().getString("MONGO.AUTH.AUTH-DATABASE");

    private boolean connected;

    private MongoCollection<Document> playerCollection;

    public void connect() {
        try {
            plugin.getLogger().info("Connecting to MongoDB...");
            if (authentication) {
                MongoCredential mongoCredential = MongoCredential.createCredential(this.user, this.authDatabase, this.password.toCharArray());
                this.client = new MongoClient(new ServerAddress(this.host, this.port), Collections.singletonList(mongoCredential));
                this.connected = true;
                Bukkit.getConsoleSender().sendMessage("§aSuccessfully connected to MongoDB.");
            } else {
                this.client = new MongoClient(new ServerAddress(this.host, this.port));
                this.connected = true;
                Bukkit.getConsoleSender().sendMessage("§aSuccessfully connected to MongoDB.");
            }
            this.mongoDatabase = this.client.getDatabase(this.database);
            this.playerCollection = this.mongoDatabase.getCollection("Basic-PlayerData");
        } catch (Exception e) {
            this.connected = false;
            plugin.setDisableMessage("An error has occured on -> MongoDB");
            Bukkit.getConsoleSender().sendMessage("§eDisabling Basic because an error occurred while trying to connected to MongoDB.");
            Bukkit.getPluginManager().disablePlugins();
            Bukkit.shutdown();
        }
    }

    public void reconnect() {
        this.client.close();
        try {
            if (authentication) {
                MongoCredential mongoCredential = MongoCredential.createCredential(this.user, this.authDatabase, this.password.toCharArray());
                this.client = new MongoClient(new ServerAddress(this.host, this.port), Collections.singletonList(mongoCredential));
            } else {
                this.client = new MongoClient(new ServerAddress(this.host, this.port));
            }
            this.mongoDatabase = this.client.getDatabase(this.database);
            this.playerCollection = this.mongoDatabase.getCollection("Basic-PlayerData");
            Bukkit.getConsoleSender().sendMessage("§aSuccessfully re-connected to MongoDB.");
        } catch (Exception e) {
            plugin.setDisableMessage("An error has occurred on -> MongoDB");
        }
    }

    public void disconnect() {
        if (this.client != null) {
            plugin.getLogger().info("[MongoDB] Disconnecting...");
            this.client.close();
            this.connected = false;
            plugin.getLogger().info("[MongoDB] Successfully disconnected.");
        }
    }
}
