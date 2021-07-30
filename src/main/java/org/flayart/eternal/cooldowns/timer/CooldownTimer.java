package org.flayart.eternal.cooldowns.timer;

import java.util.UUID;

public interface CooldownTimer {
    
    boolean isActive();
    
    long getTimeLeft();
    
    long getEndTime();
    
    UUID getUUID();
    
    void reset();
    
    void whenComplete(Runnable runnable);
}
