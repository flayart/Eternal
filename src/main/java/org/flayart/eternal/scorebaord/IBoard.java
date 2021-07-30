package org.flayart.eternal.scorebaord;

import org.bukkit.entity.Player;

import java.util.List;

public interface IBoard {
    
    String getTitle(Player player);
    
    List<String> getLines(Player player);
}
