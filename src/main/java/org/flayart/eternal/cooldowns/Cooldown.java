package org.flayart.eternal.cooldowns;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;
import org.flayart.eternal.Eternal;
import org.flayart.eternal.cooldowns.timer.Timer;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown extends Timer {
    
    private long endTime;
    private UUID uuid;
    private final HashMap<UUID, Cooldown> cooldowns = Maps.newHashMap();
    
    private Cooldown(UUID uuid, long endTime) {
        this.uuid = uuid;
        this.endTime = endTime;
        
        cooldowns.put(uuid, this);
    }
    
    public static Cooldown newCooldown(Player player, long endTime) {
        Cooldown cooldown = new Cooldown(player.getUniqueId(), endTime);
        
        cooldown.setEndTime(endTime);
        return cooldown;
    }
    
    public void setEndTime(long endTime) {
        this.endTime = endTime + System.currentTimeMillis();
    }
    
    @Override
    public void reset() {
        assert Eternal.COOLDOWN_LIST.contains(this);
        Eternal.COOLDOWN_LIST.remove(this);
        
        assert cooldowns.containsKey(uuid);
        cooldowns.remove(uuid);
    }
    
    @Override
    public long getEndTime() {
        return endTime;
    }
    
    @Override
    public UUID getUUID() {
        return uuid;
    }
}
