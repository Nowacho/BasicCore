package dev.wacho.basic.board.tablist.head;

import dev.wacho.basic.Basic;
import dev.wacho.basic.board.tablist.Tablist;
import dev.wacho.basic.utils.FileConfig;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class HeadManager {

    private final Basic plugin;
    private final Map<String, Head> heads;
    private final Set<String> headNames;

    public HeadManager(Basic plugin) {
        this.plugin = plugin;
        this.heads = new HashMap<>();
        this.headNames = new HashSet<>();
        this.loadOrRefresh();
    }

    public boolean exists(String name) {
        return heads.containsKey(name);
    }

    public Head getHead(String name) {
        return heads.get(name);
    }

    public Head getHead(Player player, String text) {
        if (text.contains("%player-head%")) {
            return this.getPlayerHead(player);
        } else {
            Head head = getHead(text);
            if (head == null) return this.getDefault();
            return head;
        }
    }

    /*public Head getHead(Player player, String text) {
        Head head = getHead(text);
        if (text.contains("%player-head%")) return this.getPlayerHead(player);
        if (text.contains("status-head")) return this.getServerStatusHead(text);
        if (head == null) return this.getDefault();
        return head;
    }*/

    public Head getPlayerHead(Player player) {
        return Tablist.getInstance().getTablistVersion().getHead(player);
    }

    public Head getDefault() {
        return heads.getOrDefault("%default-head%", null);
    }

    public void loadOrCreate(String name, ConfigurationSection section) {
        String headName = "%" + name + "-head%";
        Head head = exists(headName) ? getHead(headName) : new Head();
        head.setName(headName);
        head.setValue(section.getString(name + ".VALUE"));
        head.setSignature(section.getString(name + ".SIGNATURE"));

        if (!exists(headName)) heads.put(headName, head);
    }

    public void loadOrRefresh() {
        FileConfig tablistHeadsFile = plugin.getTabHeadFile();
        ConfigurationSection section = tablistHeadsFile.getConfiguration().getConfigurationSection("HEADS");

        for (String headName : section.getKeys(false)) {
            loadOrCreate(headName, section);
        }

        for (Head head : heads.values()) {
            String headName = head.getName();
            String headSection = headName
                    .replace("-head", "")
                    .replace("%", "")
                    .replace("%", "");

            if (!section.contains(headSection)) {
                headNames.add(headName);
            }
        }

        headNames.forEach(heads::remove);
        headNames.clear();
    }
}
