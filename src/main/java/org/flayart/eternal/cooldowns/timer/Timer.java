package org.flayart.eternal.cooldowns.timer;

import org.bukkit.Bukkit;

import java.text.DecimalFormat;
import java.util.concurrent.CompletableFuture;

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
        CompletableFuture.runAsync(() -> {
           if(isActive()) return;
           
           runnable.run();
        });
    }
}
