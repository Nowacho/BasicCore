package dev.wacho.basic.database.redis;

import dev.wacho.basic.Basic;
import dev.wacho.basic.database.redis.payload.RedisListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Getter
public class RedisManager {

    JedisPool jedisPool;

    RedisListener redisListener;

    private final String ip = Basic.getInst().getConfigFile().getString("HOST");

    private final int port = Basic.getInst().getConfigFile().getInt("PORT");

    private final String password = Basic.getInst().getConfigFile().getString("AUTHENTICATION.PASSWORD");

    private final boolean auth = Basic.getInst().getConfigFile().getBoolean("AUTHENTICATION.ENABLED");

    @Getter
    private boolean active = false;

    public void connect() {
        try {
            Basic.getInst().getLogger().info("Connecting to redis");
            this.jedisPool = new JedisPool(this.ip, this.port);
            Jedis jedis = this.jedisPool.getResource();
            if (auth) {
                if (password != null || !password.equals(""))
                    jedis.auth(this.password);
            }
            this.redisListener = new RedisListener();
            (new Thread(() -> jedis.subscribe(this.redisListener, "Zoom"))).start();
            jedis.connect();
            active = true;
            Bukkit.getConsoleSender().sendMessage("§aSuccessfully connect to §4Redis.");
        } catch (Exception e) {
            if (Basic.getInst().isDebug()) {
                e.printStackTrace();
            }
            Bukkit.getConsoleSender().sendMessage("§6[Zoom] An error occurred while trying to connect to Redis.");
            active = false;
        }
    }

    public void disconnect() {
        Basic.getInst().getLogger().info("[Redis] Disconnecting...");
        this.redisListener.unsubscribe();
        jedisPool.getResource().close();
        jedisPool.destroy();
        Basic.getInst().getLogger().info("[Redis] Disconnecting Successfully");
    }

    public void write(String json) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            if (auth) {
                if (password != null || !password.equals(""))
                    jedis.auth(this.password);
            }
            jedis.publish("Zoom", json);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
