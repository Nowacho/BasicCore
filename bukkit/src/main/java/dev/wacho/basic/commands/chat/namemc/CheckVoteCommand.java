package dev.wacho.basic.commands.chat.namemc;

import dev.wacho.basic.Basic;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.Utils;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckVoteCommand extends BaseCommand {

    public CheckVoteCommand() {
        super("checkvote", null, "namemccheck");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        PlayerData playerData = PlayerData.getPlayerData(player.getUniqueId());

        if (args.length == 0) {
            if (playerData == null) return false;
            if (playerData.isVote()) {
                sender.sendMessage(CC.translate("&cYou already voted for the server!"));
            } else {
                sender.sendMessage(CC.translate("&aChecking....."));
                if (Utils.checkPlayerVote(player.getUniqueId())) {
                    sender.sendMessage(CC.translate("&aCorrect verification!"));
                    playerData.setVote(true);
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), Basic.getInst().getConfigFile().getString("NAME-MC-CHECK.COMMAND")
                                    .replace("%player%", player.getName()));
                } else {
                    sender.sendMessage(CC.translate("&cAre you sure you have voted for the server?"));
                }
            }
        }
        return true;
    }
}
