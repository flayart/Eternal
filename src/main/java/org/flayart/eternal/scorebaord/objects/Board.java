package org.flayart.eternal.scorebaord.objects;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.flayart.eternal.scorebaord.BoardProvider;
import org.flayart.eternal.utils.ChatUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {
    
    private final Player player;
    private final BoardProvider boardProvider;
    private final Objective objective;
    private final Team team;
    
    public Board(Player player, BoardProvider boardProvider) {
        this.player = player;
        this.boardProvider = boardProvider;
        this.objective = getPlayerObjective();
        this.team = getPlayerTeam();
        
        setupObjective();
        setupTeam();
    }
    
    private Objective getPlayerObjective() {
        if (getScoreboard().getObjective("board") == null)
            return getScoreboard().registerNewObjective("board", "dummy", Component.translatable(boardProvider.getIBoard().getTitle(player)));
        return getScoreboard().getObjective("board");
    }
    
    private Team getPlayerTeam() {
        if (getScoreboard().getTeam("board") == null)
            return getScoreboard().registerNewTeam("board");
        return getScoreboard().getTeam("board");
    }
    
    private void setupTeam() {
        team.setAllowFriendlyFire(true);
        team.setCanSeeFriendlyInvisibles(false);
    }
    
    private void setupObjective() {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }
    
    private Scoreboard getScoreboard() {
        return player.getScoreboard();
    }
    
    public void removeScoreboard() {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }
    
    public void updateScoreboard() {
        Objects.requireNonNull(boardProvider);
        if (!player.isOnline()) return;
        
        String title = boardProvider.getIBoard().getTitle(player);
        List<String> lines = boardProvider.getIBoard().getLines(player)
                .stream().map(ChatUtils::color).collect(Collectors.toList());
        
        objective.displayName(Component.translatable(ChatUtils.color(title.length() > 32 ? title.substring(0, 32) : title)));
        
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            
            objective.getScore(line).setScore(15 - i);
        }
    }
    
}
