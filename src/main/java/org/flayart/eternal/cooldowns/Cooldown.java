package org.flayart.eternal.cooldowns;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;
import org.flayart.eternal.cooldowns.timer.Timer;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown extends Timer {
    
    private long endTime;
    private final HashMap<UUID, Cooldown> cooldowns = Maps.newHashMap();
    
    public Cooldown(long endTime) {
        this.endTime = endTime;
    }
    
    public void addPlayer(Player player) {
        cooldowns.put(player.getUniqueId(), this);
    }
    
    public void removePlayer(Player player) {
        cooldowns.remove(player.getUniqueId());
    }
    
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    
    public void reset() {
        this.endTime = System.currentTimeMillis();
    }
    
    @Override
    public long getEndTime() {
        return endTime;
    }
}
