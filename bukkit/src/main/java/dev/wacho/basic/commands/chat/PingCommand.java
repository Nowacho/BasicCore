package dev.wacho.basic.commands.chat;

import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.Utils;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand extends BaseCommand {

    private final Basic plugin = Basic.getInst();

    public PingCommand() {
        super("ping", null, "ms");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        String sound = plugin.getMessageFile().getString("PING-MESSAGES.SOUND");

        int ping = Utils.getPing(player);

        if (args.length == 0) {
            sender.sendMessage(CC.translate(plugin.getMessageFile().getString("MESSAGES.PING-MESSAGES.DEFAULT").replace("%ping%", String.valueOf(ping))));
            if (!(sound.equalsIgnoreCase("none") || sound == null)) {
                player.playSound(player.getLocation(), Sound.valueOf(plugin.getMessageFile().getString("MESSAGES.PING-MESSAGES.SOUND")), 2F, 2F);
            }
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cCouldn't find player.");
                return false;
            }

            int pingTarget = Utils.getPing(target);
            sender.sendMessage(CC.translate(plugin.getMessageFile().getString("MESSAGES.PING-MESSAGES.OTHER")
                    .replace("%ping%", String.valueOf(pingTarget))
                    .replace("%target%", target.getDisplayName())));
            Utils.sendPlayerSound(player, sound);
        }
        return true;
    }
}
