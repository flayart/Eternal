package org.flayart.eternal.handlers;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.flayart.eternal.scorebaord.IBoard;
import org.flayart.eternal.scorebaord.objects.Board;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Getter
public class BoardManager {
    
    private final Map<UUID, Board> scoreboard;
    private IBoard iBoard;
    
    public BoardManager() {
        this.scoreboard = Maps.newConcurrentMap();
    }
    
    public Optional<Board> getBoard(UUID uuid) {
        return Optional.ofNullable(scoreboard.get(uuid));
    }
    
    public boolean hasBoard(UUID uuid) {
        return scoreboard.containsKey(uuid);
    }
}
