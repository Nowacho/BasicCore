package dev.wacho.basic.utils;

import com.google.common.base.Joiner;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.lang.Lang;
import lombok.SneakyThrows;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Utils {

    public static int getPing(Player p) {
        try {
            String version = Bukkit.getServer().getClass().getPackage().getName().substring(23);
            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
            Object handle = craftPlayer.getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
            return (Integer) handle.getClass().getDeclaredField("ping").get(handle);
        } catch (Exception e) {
            return -1;
        }
    }

    public static void globalBroadcast(Player player, String message) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Message");
        output.writeUTF("ALL");
        output.writeUTF(message);

        player.sendPluginMessage(Basic.getInst(), "BungeeCord", output.toByteArray());
    }

    public static String getDisplayName(UUID uuid) {
        if (uuid == null) return "Console";
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        if (offlinePlayer != null) {
            return offlinePlayer.getName();
        } else {
            return "Console";
        }
    }

    public static String getStaffName(CommandSender sender) {
        return sender instanceof Player ? ((Player) sender).getPlayer().getDisplayName() : "§4§lConsole";
    }

    public static void sendPlayerSound(Player p, String sound) {
        if (!(sound.equalsIgnoreCase("none") || sound == null)) {
            p.playSound(p.getLocation(), Sound.valueOf(sound), 2F, 2F);
        }
    }

    @SneakyThrows
    public static String getCountry(String ip) throws Exception{
        URL url = new URL("http://ip-api.com/json/" + ip);
        BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder entirePage = new StringBuilder();
        String inputLine;
        while ((inputLine = stream.readLine()) != null) entirePage.append(inputLine);
        stream.close();
        if (!(entirePage.toString().contains("\"country\":\""))) return null;

        return entirePage.toString().split("\"country\":\"")[1].split("\",")[0];
    }

    public static boolean checkPlayerVote(UUID uuid) {
        String pageRequest = "https://api.namemc.com/server/" + Lang.SERVER_IP + "/likes?profile=" + uuid.toString();
        try {
            URL url = new URL(pageRequest);
            ArrayList<Object> lines = new ArrayList();
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
                lines.add(line);
            if (lines.contains("true")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException exception) {
            Bukkit.getConsoleSender().sendMessage("§cAn error occurred while checking vote on name-mc");
        }

        return false;
    }

    public static int getNumLikes() throws IOException {
        URL url = new URL("https://api.namemc.com/server/" + Lang.SERVER_IP + "/likes");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            JSONArray likesArray = new JSONArray(response.toString());
            return likesArray.length();
        }
    }
}
