package org.flayart.eternal.cooldowns.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import org.flayart.eternal.Eternal;
import org.flayart.eternal.cooldowns.Cooldown;

public class CompleteTask extends BukkitRunnable {
    
    @Override
    public void run() {
        if (Eternal.COOLDOWN_LIST.isEmpty()) return;
        
        for (Cooldown cooldown : Eternal.COOLDOWN_LIST) {
            if (cooldown.isActive()) continue;
            
            cooldown.getRunnable().run();
            cooldown.reset();
        }
        
        Eternal.COOLDOWN_LIST.removeIf(cooldown ->  !cooldown.isActive());
    }
}
