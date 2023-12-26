package dev.wacho.proxy.handler;

import dev.wacho.proxy.util.ColorUtil;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Arrays;
import java.util.List;

public class PlayerHandler implements Listener {

    private final List<String> blockedCommands = Arrays.asList(
            "/pl", "/plugins", "/minecraft:me", "/about", "/bukkit:about",
            "/bukkit:help", "/bukkit:?", "/bukkit:me", "/bukkit:pl",
            "/bukkit:plugins", "/bukkit:ver", "/bukkit:version", "/help",
            "/icanhasbukkit", "/?", "/calc", "/eval", "//eval", "//calc",
            "/calc", "/eval", "/plugin", "/ver", "/version",
            "/aqua", "/core", "/aquacore", "/holo", "/holograms", "/desenthologram",
            "/desentholograms", "/fawe", "/oaanmdalolo1lnanhj2opa", "/server", "/minecraft:me",
            "/bukkit:tell", "/minecraft:tell", "help", "oaanmdalolo1lnanhj2opa", "dh", "bungee", "nullcordx"
    );

    @EventHandler
    public void onChat(ChatEvent event) {
        if (event.isCommand() && event.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            String message = event.getMessage().toLowerCase();

            if (blockedCommands.stream().anyMatch(message::startsWith) && !player.hasPermission("basic.command.bypass")) {
                event.setCancelled(true);
                player.sendMessage(ColorUtil.colorize("&cThis command is not recognized on this server."));
            }
        }
    }
}
