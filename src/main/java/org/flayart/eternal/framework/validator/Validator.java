package org.flayart.eternal.framework.validator;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.flayart.eternal.framework.exception.CommandException;

import java.util.UUID;

@UtilityClass @SuppressWarnings("deprecation")
public class Validator {

    public void condition(boolean condition, String error) {
        if(condition) throw new CommandException(error);
    }

    public void notCondition(boolean condition, String error) {
        if(!condition) throw new CommandException(error);
    }

    public void hasPermission(Player player, String permission, String error) {
        if(!player.hasPermission(permission)) throw new CommandException(error);
    }

    public void isNull(Object obj, String error) {
        if(obj == null) throw new CommandException(error);
    }

    public void minArgs(String[] args, int minargs, String error) {
        if(args.length <= minargs) throw new CommandException(error);
    }

    public void isOnline(String player, String error) {
        if(!Bukkit.getOfflinePlayer(player).isOnline()) throw new CommandException(error);
    }

    public void isOnline(UUID uuid, String error) {
        if(!Bukkit.getOfflinePlayer(uuid).isOnline()) throw new CommandException(error);
    }

    public Player getPlayerSender(CommandSender sender, String error) {
        if(sender instanceof Player) return (Player) sender;
        else throw new CommandException(error);
    }

    public Block getTargetBlock(Player player, int distance, String error) {
        if(player.getTargetBlock(distance) == null) throw new CommandException(error);
        else return player.getTargetBlock(distance);
    }
}
