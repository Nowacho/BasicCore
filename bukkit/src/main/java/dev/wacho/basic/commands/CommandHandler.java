package dev.wacho.basic.commands;

import dev.wacho.basic.Basic;
import dev.wacho.basic.commands.basic.BasicExecutor;
import dev.wacho.basic.commands.chat.*;
import dev.wacho.basic.commands.chat.namemc.CheckVoteCommand;
import dev.wacho.basic.commands.chat.namemc.VoteCommand;
import dev.wacho.basic.commands.staff.AnnounceCommand;
import dev.wacho.basic.commands.staff.CountryCommand;
import dev.wacho.basic.commands.staff.InstanceCommand;
import dev.wacho.basic.commands.staff.PlayerInfoCommand;
import dev.wacho.basic.commands.staff.coins.CoinsCommand;
import dev.wacho.basic.commands.staff.coins.CoinsManagerCommand;
import dev.wacho.basic.misc.BasicUtils;
import dev.wacho.basic.misc.shop.ShopCommand;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.command.BaseCommand;
import dev.wacho.basic.utils.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class CommandHandler {

    private JavaPlugin javaPlugin;
    private CommandMap commandMap;

    public CommandHandler() {
        javaPlugin = Basic.getInst();
        registerCommand(new BasicExecutor());

        registerCommand(new HubCommand());
        registerCommand(new PingCommand());
        registerCommand(new CheckVoteCommand());
        registerCommand(new VoteCommand());
        registerCommand(new ShopCommand());
        registerCommand(new SettingsCommand());
        registerCommand(new CosmeticCommand());
        registerCommand(new TestCosmeticCommand());
        registerCommand(new TagsCommand());
        registerCommand(new CoinsCommand());
        registerCommand(new CoinsManagerCommand(), BasicUtils.PERMISSION + "coins");
        //registerCommand(new AnnounceCommand(), BasicUtils.PERMISSION + "announce");
        registerCommand(new PlayerInfoCommand(), BasicUtils.PERMISSION + "playerinfo");
        registerCommand(new InstanceCommand(), BasicUtils.PERMISSION + "instance");
        registerCommand(new CountryCommand(), BasicUtils.PERMISSION + "country");
    }

    private void registerCommand(BaseCommand baseCommand) {
        registerCommand(baseCommand, null);
    }
    private void registerCommand(BaseCommand baseCommand, String permission) {
        PluginCommand command = getCommand(baseCommand.getName(), javaPlugin);

        command.setPermissionMessage(Lang.NO_PERMISSION);

        if (permission != null) {
            command.setPermission(permission.toLowerCase());
        }

        if (baseCommand.getDescription() != null) {
            command.setDescription(baseCommand.getDescription());
        }

        command.setAliases(Arrays.asList(baseCommand.getAliases()));

        command.setExecutor(baseCommand);
        command.setTabCompleter(baseCommand);

        if (!getCommandMap().register(baseCommand.getName(), command)) {
            command.unregister(getCommandMap());
            getCommandMap().register(baseCommand.getName(), command);
        }
    }

    private CommandMap getCommandMap() {
        if (commandMap != null) {
            return commandMap;
        }

        try {
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");
            field.setAccessible(true);

            commandMap = (CommandMap) field.get(Bukkit.getPluginManager());
        } catch (Exception ignored) {
        }

        return commandMap;
    }

    private PluginCommand getCommand(String name, Plugin owner) {
        PluginCommand command = null;

        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);

            command = constructor.newInstance(name, owner);
        } catch (Exception ignored) {
        }

        return command;
    }
}