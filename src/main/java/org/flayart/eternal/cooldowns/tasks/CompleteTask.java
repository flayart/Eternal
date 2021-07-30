package org.flayart.eternal.cooldowns.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import org.flayart.eternal.cooldowns.Cooldown;

public class CompleteTask extends BukkitRunnable {
    
    @Override
    public void run() {
        for (Cooldown cooldown : Cooldown.COOLDOWN_LIST) {
            if(cooldown.isActive()) continue;
            
            cooldown.getRunnable().run();
        }
    }
}
