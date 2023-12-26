package dev.wacho.proxy.command.staff;

import dev.wacho.proxy.BasicProxy;
import dev.wacho.proxy.util.ColorUtil;
import me.activated.core.plugin.AquaCore;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.bukkit.command.ConsoleCommandSender;

import java.util.stream.Collectors;

public class WhoisCommand extends Command {

    private final BasicProxy inst;

    public WhoisCommand(BasicProxy inst) {
        super("whois", "basic.command.whois");
        this.inst = inst;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!commandSender.hasPermission(getPermission()) && !(commandSender instanceof ConsoleCommandSender)) {
            commandSender.sendMessage(ColorUtil.colorize("&cYou do not have permissions to use this command."));
            return;
        }

        if (strings.length == 0) {
            commandSender.sendMessage(ColorUtil.colorize("&cUsage: /whois <player>"));
            return;
        }

        final String target = strings[0];
        final ProxiedPlayer targetP = BasicProxy.getInst().getProxy().getPlayer(target);
        if (targetP == null) {
            commandSender.sendMessage(ColorUtil.colorize("&cThis player isnt online!"));
            return;
        }


        for (String whoisMessage : BasicProxy.getInst().getConfig().getStringList("WHOIS-MESSAGE").stream()
                .map(a -> a.replace("%server%", String.valueOf(targetP.getServer().getInfo().getName())))
                .map(a -> a.replace("%player_name%" , String.valueOf(targetP.getName())))
                .map(a -> a.replace("%rank%" , String.valueOf(AquaCore.INSTANCE.getPlayerManagement().getPlayerData(targetP.getUniqueId()).getHighestRank().getPrefix())))
                .map(a -> a.replace("%ping%", String.valueOf(targetP.getPing())))
                .map(a -> a.replace("%uuid%", String.valueOf(targetP.getUniqueId())))
                .map(a -> a.replace("%ip%", String.valueOf(targetP.getAddress())))
                .map(a -> a.replace("%customname%", String.valueOf(targetP.getDisplayName())))
                .map(a -> a.replace("%perms%", String.valueOf(targetP.getPermissions())))
                .map(a -> a.replace("%location%", String.valueOf(targetP.getLocale().getCountry())))
                .collect(Collectors.toList())){
            commandSender.sendMessage(ColorUtil.colorize(whoisMessage));
        }
    }
}
