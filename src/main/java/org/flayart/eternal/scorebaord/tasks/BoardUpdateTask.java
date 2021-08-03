package org.flayart.eternal.scorebaord.tasks;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.flayart.eternal.scorebaord.manager.BoardManager;

import java.util.UUID;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class BoardUpdateTask extends BukkitRunnable {
    
    private final Predicate<UUID> isOnline = uuid -> Bukkit.getPlayer(uuid) != null;
    
    private final BoardManager boardManager;
    
    @Override
    public void run() {
        boardManager.getScoreboards()
                .entrySet().stream().filter(uuid -> isOnline.test(uuid.getKey()))
                .forEach(map -> map.getValue().updateScoreboard());
    }
}
