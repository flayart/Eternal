package org.flayart.eternal.scorebaord.objects;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;
import org.flayart.eternal.scorebaord.BoardProvider;
import org.flayart.eternal.utils.ChatUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class Board {
    
    private static final String[] CACHE = new String[ChatColor.values().length];
    
    private final Player player;
    private final BoardProvider boardProvider;
    private Objective objective;
    private Team team;
    
    public Board(Player player, BoardProvider boardProvider) {
        this.player = player;
        this.boardProvider = boardProvider;
        
        setup();
    }
    
    private void setup() {
        if (!(player.getScoreboard().getTeam("board") == null)) {
            team = player.getScoreboard().getTeam("board");
        } else {
            team = player.getScoreboard().registerNewTeam("board");
        }
        
        if (!(player.getScoreboard().getObjective("board") == null)) {
            objective = player.getScoreboard().getObjective("board");
        } else {
            objective = player.getScoreboard().registerNewObjective("board", "dummy", Component.translatable(boardProvider.getIBoard().getTitle(player)));
        }
    }
    
    public void removeScoreboard() {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }
    
    public void updateScoreboard() {
        Objects.requireNonNull(boardProvider);
        
        if (!player.isOnline()) {
            removeScoreboard();
            return;
        }
        
        String title = boardProvider.getIBoard().getTitle(player);
        List<String> lines = boardProvider.getIBoard().getLines(player)
                .stream().map(ChatUtils::color).collect(Collectors.toList());
        
        if (player.getScoreboard().getEntries().size() != lines.size())
            player.getScoreboard().getEntries().forEach(this::remove);
        
        objective.displayName(Component.translatable(ChatUtils.color(title.length() > 32 ? title.substring(0, 32) : title)));
        
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Entry entry = Entry.set(line);
            Team team = player.getScoreboard().getTeam(CACHE[i]);
            
            if (team == null) {
                team = player.getScoreboard().registerNewTeam(CACHE[i]);
                team.addEntry(team.getName());
            }
            
            team.prefix(Component.translatable(entry.getPrefiix()));
            team.suffix(Component.translatable(entry.getSuffix()));
            
            objective.getScore(team.getName()).setScore(15 - i);
        }
    }
    
    public void remove(String key) {
        player.getScoreboard().resetScores(key);
    }
    
    static {
        IntStream.range(0, 15).forEach(i -> CACHE[i] = ChatColor.values()[i].toString() + ChatColor.RESET);
    }
    
}
