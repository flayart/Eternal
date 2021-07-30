package org.flayart.eternal.cooldowns;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.entity.Player;
import org.flayart.eternal.cooldowns.timer.Timer;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Cooldown extends Timer {
    
    private long endTime;
    private final HashMap<UUID, Cooldown> cooldowns = Maps.newHashMap();
    public static final List<Cooldown> COOLDOWN_LIST = Lists.newArrayList();
    
    public Cooldown(long endTime) {
        this.endTime = endTime;
        
        COOLDOWN_LIST.add(this);
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
    
    @Override
    public void reset() {
        this.endTime = System.currentTimeMillis();
        COOLDOWN_LIST.remove(this);
    }
    
    @Override
    public long getEndTime() {
        return endTime;
    }
}
