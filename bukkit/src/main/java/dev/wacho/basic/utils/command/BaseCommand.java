package dev.wacho.basic.utils.command;

import dev.wacho.basic.Basic;
import dev.wacho.basic.misc.BasicUtils;
import dev.wacho.basic.utils.CC;
import lombok.Getter;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseCommand implements CommandExecutor, TabCompleter {

    public Basic basic = Basic.getInst();

    private String name, description;
    private String[] aliases;

    @Getter
    private List<CommandArgument> commandArguments = new ArrayList<>();

    public BaseCommand(String name) {
        this(name, null);
    }

    public BaseCommand(String name, String description) {
        this(name, description, ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public BaseCommand(String name, String description, String... aliases) {
        this.name = name;
        this.description = description;
        this.aliases = Arrays.copyOf(aliases, aliases.length);
    }

    private int totalPages(CommandSender sender) {
        int pageSize = 5; // Cambia esto al número deseado de comandos por página
        int totalCommands = 0;

        for (CommandArgument commandArgument : commandArguments) {
            if (commandArgument.getPermission() == null) {
                totalCommands++;
            } else if (sender instanceof CommandSender && ((CommandSender) sender).hasPermission(commandArgument.getPermission())) {
                totalCommands++;
            }
        }

        return (int) Math.ceil((double) totalCommands / pageSize);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int pageSize = 5;

        if (args.length == 0) {
            int totalPages = totalPages(sender);
            int currentPage = 1;
            int shownCommands = 0;

            if (totalPages > 1) {
                sender.sendMessage(CC.translate("&8&m----------------------------------------"));
                if (currentPage == 1) {
                    sender.sendMessage(CC.translate("&c&l" + command.getName() + " Help &7(Page " + currentPage + "/" + totalPages + " &a>&7)"));
                } else if (currentPage < totalPages) {
                    sender.sendMessage(CC.translate("&c&l" + command.getName() + " Help &7(&c< &7Page " + currentPage + "/" + totalPages + " &a>&7)"));
                } else {
                    sender.sendMessage(CC.translate("&c&l" + command.getName() + " Help &7(&c< &7Page " + currentPage + "/" + totalPages + ")"));
                }
                sender.sendMessage(CC.translate(""));
                sender.sendMessage(CC.translate("&f<> = Required ┃ [] = Optional"));
                sender.sendMessage(CC.translate(""));

                for (CommandArgument commandArgument : commandArguments) {
                    if (commandArgument.getPermission() == null || (sender instanceof CommandSender && ((CommandSender) sender).hasPermission(commandArgument.getPermission()))) {
                        if (shownCommands >= pageSize * (currentPage - 1) && shownCommands < pageSize * currentPage) {
                            sender.sendMessage(CC.translate(" &f" + commandArgument.getUsage(label) + (commandArgument.getDescription() != null ? " &7> " + commandArgument.getDescription() : "")));
                        }
                        shownCommands++;
                    }
                }

                sender.sendMessage(CC.translate(""));
                sender.sendMessage(CC.translate("&7To view other pages, use '&c/" + command.getName() + " help <page#>&7'."));
                sender.sendMessage(CC.translate("&8&m----------------------------------------"));
            } else {
                sender.sendMessage(CC.translate("&8&m----------------------------------------"));
                sender.sendMessage(CC.translate("&c&l" + command.getName() + " Help &7(Page 1/1)"));
                sender.sendMessage(CC.translate(""));
                sender.sendMessage(CC.translate("&f<> = Required ┃ [] = Optional"));
                sender.sendMessage(CC.translate(""));

                for (CommandArgument commandArgument : commandArguments) {
                    if (commandArgument.getPermission() == null || (sender instanceof CommandSender && ((CommandSender) sender).hasPermission(commandArgument.getPermission()))) {
                        sender.sendMessage(CC.translate(" &f" + commandArgument.getUsage(label) + (commandArgument.getDescription() != null ? " &7> " + commandArgument.getDescription() : "")));
                    }
                }

                sender.sendMessage(CC.translate(""));
                sender.sendMessage(CC.translate("&7To view other pages, use '&c/" + command.getName() + " help <page#>&7'."));
                sender.sendMessage(CC.translate("&8&m----------------------------------------"));
            }
            return true;
        }

        if (args.length >= 2 && args[0].equalsIgnoreCase("help")) {
            try {
                int page = Integer.parseInt(args[1]);
                int totalPages = totalPages(sender);
                int shownCommands = 0;

                if (page < 1) {
                    page = 1;
                } else if (page > totalPages) {
                    page = totalPages;
                }

                sender.sendMessage(CC.translate("&8&m----------------------------------------"));
                if (totalPages > 1) {
                    if (page == 1) {
                        sender.sendMessage(CC.translate("&c&l" + command.getName() + " Help &7(Page " + page + "/" + totalPages + " &a>&7)"));
                    } else if (page < totalPages) {
                        sender.sendMessage(CC.translate("&c&l" + command.getName() + " Help &7(&c< &7Page " + page + "/" + totalPages + " &a>&7)"));
                    } else {
                        sender.sendMessage(CC.translate("&c&l" + command.getName() + " Help &7(&c< &7Page " + page + "/" + totalPages + ")"));
                    }
                } else {
                    sender.sendMessage(CC.translate("&c&l" + command.getName() + " Help &7(Page " + page + "/" + totalPages + ")"));
                }
                sender.sendMessage(CC.translate(""));
                sender.sendMessage(CC.translate("&f<> = Required ┃ [] = Optional"));
                sender.sendMessage(CC.translate(""));

                for (CommandArgument commandArgument : commandArguments) {
                    if (commandArgument.getPermission() == null || (sender instanceof CommandSender && ((CommandSender) sender).hasPermission(commandArgument.getPermission()))) {
                        if (shownCommands >= pageSize * (page - 1) && shownCommands < pageSize * page) {
                            sender.sendMessage(CC.translate(" &f" + commandArgument.getUsage(label) + (commandArgument.getDescription() != null ? " &7> " + commandArgument.getDescription() : "")));
                        }
                        shownCommands++;
                    }
                }

                sender.sendMessage(CC.translate(""));
                if (totalPages > 1) {
                    sender.sendMessage(CC.translate("&7To view other pages, use '&c/" + command.getName() + " help <page#>&7'."));
                }
                sender.sendMessage(CC.translate("&8&m----------------------------------------"));
                return true;
            } catch (NumberFormatException e) {
                sender.sendMessage(CC.translate("&cInvalid page number. Please enter a valid page number."));
            }
        } else {
            CommandArgument commandArgument = getArgument(args[0]);
            if (commandArgument == null || (commandArgument.getPermission() != null && (sender instanceof CommandSender && !((CommandSender) sender).hasPermission(commandArgument.getPermission())))) {
                sender.sendMessage(CC.translate("&cNo argument found."));
            } else {
                if (commandArgument.onlyplayers && sender instanceof ConsoleCommandSender) {
                    Bukkit.getConsoleSender().sendMessage(CC.translate("&cThis command can only be executed by players."));
                    return false;
                }
                commandArgument.onExecute(sender, label, args);
            }
        }
        return true;
    }
    
    public CommandArgument getArgument(String name) {
        for (CommandArgument commandArgument : commandArguments) {
            if (commandArgument.name.equalsIgnoreCase(name) || Arrays.asList(commandArgument.aliases).contains(name.toLowerCase())) {
                return commandArgument;
            }
        }
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> results = new ArrayList<>();
        if (args.length < 2) {

            for (CommandArgument commandArgument : commandArguments) {
                String permission = commandArgument.permission;
                if (permission == null || sender.hasPermission(permission)) {
                    results.add(commandArgument.name);
                }
            }

            if (results.isEmpty()) {
                return null;
            }
        } else {
            CommandArgument commandArgument = getArgument(args[0]);
            if (commandArgument == null) {
                return results;
            }

            String permission = commandArgument.permission;
            if (permission == null || sender.hasPermission(permission)) {
                results = commandArgument.onTabComplete(sender, label, args);

                if (results == null) {
                    return null;
                }
            }
        }

        return BasicUtils.getCompletions(args, results);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void registerArgument(CommandArgument commandArgument) {
        this.commandArguments.add(commandArgument);
    }

}