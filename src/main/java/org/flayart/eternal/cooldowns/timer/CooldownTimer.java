package org.flayart.eternal.cooldowns.timer;

public interface CooldownTimer {
    
    boolean isActive();
    
    long getTimeLeft();
    
    long getEndTime();
    
    void whenComplete(Runnable runnable);
}
