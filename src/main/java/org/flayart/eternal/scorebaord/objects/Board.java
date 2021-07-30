package org.flayart.eternal.scorebaord.objects;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class Board {
    
    private Player player;
    private Objective objective;
    
    public Board(Player player) {
        this.player = player;
        this.objective = createObjective();
    }
    
    private Objective createObjective() {
        assert getScoreboard().getObjective("board") != null;
        return getScoreboard().registerNewObjective("board", "dummy");
    }
    
    private Scoreboard getScoreboard() {
        return player.getScoreboard();
    }
    
}
