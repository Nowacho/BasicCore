package dev.wacho.basic.commands.basic;

import dev.wacho.basic.commands.basic.argument.ReloadArgument;
import dev.wacho.basic.commands.basic.argument.VersionArgument;
import dev.wacho.basic.utils.command.BaseCommand;

public class BasicExecutor extends BaseCommand {

    public BasicExecutor() {
        super("Basic", null, "velnox");
        registerArgument(new ReloadArgument());
        registerArgument(new VersionArgument());
    }
}