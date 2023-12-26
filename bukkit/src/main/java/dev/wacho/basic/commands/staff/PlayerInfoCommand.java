package dev.wacho.basic.commands.staff;

import dev.wacho.basic.Basic;
import dev.wacho.basic.BasicAPI;
import dev.wacho.basic.player.PlayerData;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.Clickable;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import me.activated.core.plugin.AquaCoreAPI;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfoCommand extends BaseCommand {

    private final Basic plugin = Basic.getInst();

    public PlayerInfoCommand() {
        super("playerinfo", null, "pinfo", "info", "getplayer");
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

        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);
        PlayerData playerData = PlayerData.getPlayerData(target.getName());
        me.activated.core.api.player.PlayerData aquaData = AquaCoreAPI.INSTANCE.getPlayerData(player.getUniqueId());

        sender.sendMessage(CC.CHAT_BAR);
        sender.sendMessage(CC.translate("&b&lBasic &7- &fPlayer Info"));
        sender.sendMessage(CC.translate("&8 ▸ &fPlayer: &3" + target.getName()));
        sender.sendMessage(CC.translate("&8 ▸ &fHighest Rank: &b" + BasicAPI.getRankPrefix(player)));

        Clickable showPermsList = new Clickable();
        List<String> playerPerms = new ArrayList<>();
        aquaData.getPermissions().forEach(perm -> playerPerms.add(CC.translate("&f ● &b" + perm)));
        showPermsList.add(CC.translate("&8 ▸ &fPermissions: &b" + aquaData.getPermissions().size() + " &7(&fHover&7)"), StringUtils.join(playerPerms, "\n"), null);
        showPermsList.sendToPlayer(player);

        sender.sendMessage(CC.translate("&8 ▸ &fLast Server: &b" + playerData.getLastServer()));
        sender.sendMessage(CC.translate("&8 ▸ &fChat Channel: &b" + (aquaData.isStaffChat() ? "Staff Chat" : "Global Chat")));
        sender.sendMessage(CC.translate("&8 ▸ &fPrivate Messages: " + (aquaData.getMessageSystem().isMessagesToggled() ? "&aEnabled" : "&cDisabled")));
        sender.sendMessage(CC.CHAT_BAR);
        return true;
    }
}
