package org.flayart.eternal.scorebaord.manager;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.flayart.eternal.scorebaord.BoardProvider;
import org.flayart.eternal.scorebaord.listener.BoardListener;
import org.flayart.eternal.scorebaord.objects.Board;
import org.flayart.eternal.scorebaord.tasks.BoardUpdateTask;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Getter
public class BoardManager {
    
    private final Map<UUID, Board> scoreboards = Maps.newConcurrentMap();
    private final BoardProvider boardProvider;
    private final BukkitTask boardUpdateTask;
    
    public BoardManager(JavaPlugin plugin, BoardProvider boardProvider) {
        this.boardProvider = boardProvider;
        this.boardUpdateTask = new BoardUpdateTask(this).runTaskTimer(plugin, 2L, 2L);
        
        new BoardListener(plugin, this);
    }
    
    public Optional<Board> getBoard(Player player) {
        return Optional.ofNullable(scoreboards.get(player.getUniqueId()));
    }
    
    public void addScoreboard(Player player) {
        removeScoreboard(player);
        
        scoreboards.put(player.getUniqueId(), new Board(player, boardProvider));
    }
    
    public void removeScoreboard(Player player) {
        getBoard(player).ifPresent(Board::removeScoreboard);
    }
}
