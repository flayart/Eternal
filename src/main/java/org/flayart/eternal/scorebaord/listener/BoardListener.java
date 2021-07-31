package org.flayart.eternal.scorebaord.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.flayart.eternal.handlers.BoardManager;

public class BoardListener implements Listener {
    
    private final JavaPlugin plugin;
    private final BoardManager boardManager;
    
    public BoardListener(JavaPlugin plugin, BoardManager boardManager) {
        this.plugin = plugin;
        this.boardManager = boardManager;
        
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (!event.getPlayer().isOnline()) return;
            
            boardManager.addScoreboard(event.getPlayer());
        }, 2L);
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        boardManager.removeScoreboard(event.getPlayer());
    }
}
