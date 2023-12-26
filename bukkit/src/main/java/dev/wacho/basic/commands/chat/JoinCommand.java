package dev.wacho.basic.commands.chat;

import dev.wacho.basic.Basic;
import dev.wacho.basic.BasicAPI;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand extends BaseCommand {

    private final Basic plugin = Basic.getInst();

    public JoinCommand() {
        super("join", null, "joinserver");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (args.length < 1) {
            player.sendMessage(CC.translate("&cUsage: /" + label + " <serverName>"));
        } else {
            BasicAPI.getToServer(player, args[0]);
            sender.sendMessage(Lang.SERVER_CONNECTING.replace("%server%", args[0]));
        }
        return true;
    }
}
