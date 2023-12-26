package dev.wacho.basic.misc.friend.commands;

import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.command.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FriendAcceptCommand extends BaseCommand {

    public FriendAcceptCommand() {
        super("friend accept");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        PlayerData target = PlayerData.getPlayerData(player.getUniqueId());
        PlayerData senderProfile = PlayerData.getPlayerData(player.getUniqueId());

        if (!senderProfile.getFriendRequests().contains(senderProfile.friendRequestByName(target.getUuid()))) {
            sender.sendMessage(CC.translate("&cThere's no pending friend request regarding that player."));
            return false;
        }

        new FriendAddPacket(player.getUniqueId(), target.getUuid()).send();
        sender.sendMessage(CC.translate("&eYou have successfully accepted &d" + target.getName() + "'s &efriend request."));
        return true;
    }
}
