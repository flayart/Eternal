package org.flayart.eternal.cooldowns.timer;

import java.text.DecimalFormat;

public abstract class Timer implements CooldownTimer {
    
    private static DecimalFormat format;
    
    static {
        Timer.format = new DecimalFormat("#0.0");
    }
    
    protected Timer() {}
    
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
    public void whenComplete(Runnable runnable) {
        if(!isActive()) runnable.run();
    }
}
