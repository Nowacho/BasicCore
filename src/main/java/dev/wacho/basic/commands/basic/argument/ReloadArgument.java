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
        Basic.getInst().getTabFile().reload();

        sender.sendMessage(CC.translate("&4&lBasic &7configuration has been reloaded."));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&4&l" + sender.getName() + " &7has reloaded Basic configuration"));
    }
}