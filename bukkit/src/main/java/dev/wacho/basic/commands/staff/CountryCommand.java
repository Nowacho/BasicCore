package dev.wacho.basic.commands.staff;

import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.Utils;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CountryCommand extends BaseCommand {

    private final Basic plugin = Basic.getInst();

    public CountryCommand() {
        super("getcountry", null, "geoip");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.NO_CONSOLE);
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(CC.translate("&cUsage /" + label + " <player>"));
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(CC.translate("&c" + player.getName() + " isn't online."));
            return false;
        }
        String ip = player.getAddress().toString().replaceAll("/", "");
        try {
            sender.sendMessage(CC.translate(plugin.getMessageFile().getString("GEO-IP.COUNTRY")
                    .replace("%player%", player.getDisplayName())
                    .replace("%country%", (Utils.getCountry(ip) == null ? "No Found" : Utils.getCountry(ip))))
            );
        } catch (Exception exception) {
            exception.printStackTrace();
            sender.sendMessage(CC.translate("&eError in get player country"));
        }
        return true;
    }
}
