package dev.wacho.basic.commands.chat.namemc;

import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class VoteCommand extends BaseCommand {

    private final Basic plugin = Basic.getInst();

    public VoteCommand() {
        super("vote", null, "namemc");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        List<String> msg = CC.translate(plugin.getConfigFile().getStringList("NAME-MC-CHECK.MESSAGE"));
        String sound = plugin.getConfigFile().getString("NAME-MC-CHECK.SOUND");

        if (args.length == 0) {
            if (plugin.getConfigFile().getBoolean("NAME-MC-CHECK.ENABLED")) {
                player.sendMessage(StringUtils.join(msg, "\n"));
                if (sound != null || !sound.equalsIgnoreCase("none")) {
                    player.playSound(player.getLocation(), Sound.valueOf(sound), 2F, 2F);
                }
            } else {
                player.sendMessage(CC.translate("&cName-MC Verification isn't active"));
            }
        }
        return true;
    }
}
