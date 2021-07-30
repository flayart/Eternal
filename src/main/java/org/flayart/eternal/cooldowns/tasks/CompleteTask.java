package org.flayart.eternal.cooldowns.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import org.flayart.eternal.cooldowns.Cooldown;

public class CompleteTask extends BukkitRunnable {
    
    @Override
    public void run() {
        
        if (Cooldown.COOLDOWN_LIST.isEmpty()) return;
        
        for (Cooldown cooldown : Cooldown.COOLDOWN_LIST) {
            if (cooldown.isActive()) continue;
            
            cooldown.getRunnable().run();
        }
        
        Cooldown.COOLDOWN_LIST.removeIf(cooldown -> !cooldown.isActive());
    }
}
