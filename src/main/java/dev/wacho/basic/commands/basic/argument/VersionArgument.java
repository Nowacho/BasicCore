package dev.wacho.basic.commands.basic.argument;

import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.command.CommandArgument;
import org.bukkit.command.CommandSender;

public class VersionArgument extends CommandArgument {

    public VersionArgument() {
        super("version", "See Basic Version");
    }

    @Override
    public String getUsage(String s) {
        return '/' + s + ' ' + name;
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) {
        sender.sendMessage(CC.translate("&8&m----------------------------------------"));
        sender.sendMessage(CC.translate("&4&lBasic"));
        sender.sendMessage(CC.translate("&4▎ &fAuthor: &cNoWacho"));
        sender.sendMessage(CC.translate("&4▎ &fDiscord: &cdiscord.velnox.club"));
        sender.sendMessage("");
        sender.sendMessage(CC.translate("&4▎ &fVersion: &a" + Basic.getInst().getDescription().getVersion()));
        sender.sendMessage(CC.translate("&4▎ &fLast Update &c25/10/23"));
        sender.sendMessage(CC.translate("&8&m----------------------------------------"));
    }
}