package dev.wacho.basic.commands.basic.argument;

import dev.wacho.basic.Basic;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.command.CommandArgument;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class ReloadArgument extends CommandArgument {

    public ReloadArgument() {
        super("reload", "Reload Basic Config", "*");
    }

    @Override
    public String getUsage(String s) {
        return '/' + s + ' ' + name;
    }

    @Override
    public void onExecute(CommandSender sender, String label, String[] args) {
        Basic.getInst().getConfigFile().reload();
        Basic.getInst().getScoreboardFile().reload();
        Basic.getInst().getMessageFile().reload();
        Basic.getInst().getTabFile().reload();
        Basic.getInst().getHotbarFile().reload();

        sender.sendMessage(CC.translate("&b&lBasic &fconfiguration has been reloaded."));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&b&l" + sender.getName() + " &fhas reloaded Basic configuration"));
    }
}