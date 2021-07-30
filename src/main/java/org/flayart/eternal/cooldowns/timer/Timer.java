package org.flayart.eternal.cooldowns.timer;

import lombok.Getter;

import java.util.UUID;

public abstract class Timer implements CooldownTimer {
    
    @Getter
    private Runnable runnable;
    
    protected Timer() {
    }
    
    @Override
    public boolean isActive() {
        return this.getEndTime() > System.currentTimeMillis();
    }
    
    @Override
    public long getTimeLeft() {
        return this.getEndTime() - System.currentTimeMillis();
    }
    
    @Override
    public abstract long getEndTime();
    
    @Override
    public abstract UUID getUUID();
    
    @Override
    public abstract void reset();
    
    @Override
    public void whenComplete(Runnable runnable) {
        this.runnable = runnable;
    }
}
