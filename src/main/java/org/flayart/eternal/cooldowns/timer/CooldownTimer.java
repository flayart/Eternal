package org.flayart.eternal.cooldowns.timer;

public interface CooldownTimer {
    
    boolean isActive();
    
    long getTimeLeft();
    
    void reset();
    
    long getEndTime();
    
    void whenComplete(Runnable runnable);
}
