package org.flayart.eternal.framework;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.flayart.eternal.framework.annotation.CommandInfo;

import java.util.Arrays;
import java.util.List;

public abstract class CommandFramework implements CommandExecutor {
    @Getter private List<SubCommand> subCommands;

    public CommandFramework(JavaPlugin plugin) {
        if(!getClass().isAnnotationPresent(CommandInfo.class)) return;
        plugin.getCommand(getClass().getAnnotation(CommandInfo.class).value()).setExecutor(this);
        this.subCommands = Lists.newArrayList();
    }

    public void registerSubcommand(SubCommand... subCommands) {
        this.subCommands.addAll(Arrays.asList(subCommands));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        try {
            if(args.length == 0) execute(sender);
            else {
                for(SubCommand subCommand : subCommands) {
                    if(!args[0].equalsIgnoreCase(subCommand.getSubcommand())) continue;
                    if(args.length != subCommand.getMinargs()) continue;
                    subCommand.execute(sender, args);
                    break;
                }
            }
        } catch (CommandException exception) {
            sender.sendMessage(exception.getMessage());
        }

        return false;
    }

    public abstract void execute(CommandSender sender);
}
