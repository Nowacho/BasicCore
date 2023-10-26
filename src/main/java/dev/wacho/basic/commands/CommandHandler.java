package dev.wacho.basic.commands;

import dev.wacho.basic.Basic;
import dev.wacho.basic.commands.basic.BasicExecutor;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.command.BaseCommand;
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
    }

    private void registerCommand(BaseCommand axisCommand) {
        registerCommand(axisCommand, null);
    }
    private void registerCommand(BaseCommand axisCommand, String permission) {
        PluginCommand command = getCommand(axisCommand.getName(), javaPlugin);

        command.setPermissionMessage(CC.translate("&cNo permission."));

        if (permission != null) {
            command.setPermission(permission.toLowerCase());
        }

        if (axisCommand.getDescription() != null) {
            command.setDescription(axisCommand.getDescription());
        }

        command.setAliases(Arrays.asList(axisCommand.getAliases()));

        command.setExecutor(axisCommand);
        command.setTabCompleter(axisCommand);

        if (!getCommandMap().register(axisCommand.getName(), command)) {
            command.unregister(getCommandMap());
            getCommandMap().register(axisCommand.getName(), command);
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