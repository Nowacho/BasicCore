package dev.wacho.basic.commands.staff;

import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InstanceCommand extends BaseCommand {

    public InstanceCommand() {
        super("instance", null, "serverinstance", "checkinstance", "currentserver");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.NO_CONSOLE);
            return false;
        }

        sender.sendMessage(CC.CHAT_BAR);
        sender.sendMessage(CC.translate("&b&lZoom &7- &fServer Instance"));
        sender.sendMessage(CC.CHAT_BAR);
        sender.sendMessage(CC.translate("&8 ▸ &fServer: &b" + Lang.SERVER_NAME));
        sender.sendMessage(CC.translate("&8 ▸ &fVersion: &b" + Bukkit.getServer().getVersion()));
        sender.sendMessage(CC.translate("&8 ▸ &fPlayers: &b" + Bukkit.getServer().getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers()));
        sender.sendMessage(CC.CHAT_BAR);
        return true;
    }
}
