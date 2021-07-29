package org.flayart.eternal.framework.validator;

import lombok.experimental.UtilityClass;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.flayart.eternal.framework.exception.CommandException;

@UtilityClass
public class Validator {

    public void condition(boolean condition, String error) {
        if(condition) throw new CommandException(error);
    }

    public void notCondition(boolean condition, String error) {
        if(!condition) throw new CommandException(error);
    }

    public Player getPlayerSender(CommandSender sender, String error) {
        if(sender instanceof Player) return (Player) sender;
        else throw new CommandException(error);
    }
}
