package dev.wacho.proxy.command.chat;

import dev.wacho.proxy.BasicProxy;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class StreamCommand extends Command {

    public static Set<UUID> streamToggle = new HashSet<>();
    public static final Map<UUID, Long> streamUsage = new HashMap<>();

    public StreamCommand() {
        super("stream", "basic.command.stream");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("This command can only be executed by players.");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length == 0) {
            player.sendMessage("Correct usage: /stream <link>");
        } else if (args.length == 1) {
            String url = args[0];
            if (!url.toLowerCase().startsWith("https://")) {
                player.sendMessage("Invalid URL. It must start with 'https://'.");
                return;
            }

            // Cooldown logic
            if (streamUsage.containsKey(player.getUniqueId()) && streamUsage.get(player.getUniqueId()) > System.currentTimeMillis()) {
                long millisLeft = streamUsage.get(player.getUniqueId()) - System.currentTimeMillis();
                player.sendMessage("Command cooldown. Please wait " + TimeUnit.MILLISECONDS.toSeconds(millisLeft) + " seconds.");
                return;
            }

            // Broadcast logic
            for (ProxiedPlayer onlinePlayer : player.getServer().getInfo().getPlayers()) {
                onlinePlayer.sendMessage("Broadcast message: " + player.getName() + " is streaming at " + url);
            }

            // Set cooldown
            streamUsage.put(player.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(30L));
            streamToggle.add(player.getUniqueId());
        }

        //  STREAM-COMMAND:
        //    COOLDOWN: "&cYou cannot use this command for another &l%time%."
        //    INVALID:
        //      - "&cInvalid url! &cExample: &ehttps://www.twitch.tv/AstroOperations"
        //    BROADCAST:
        //      - "&7&m-----------------------------------"
        //      - "&5&l%player% &7is now streaming"
        //      - " &c%link%"
        //      - "&7&m-----------------------------------"
    }
}
