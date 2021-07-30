package org.flayart.eternal;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.flayart.eternal.cooldowns.Cooldown;
import org.flayart.eternal.cooldowns.tasks.CompleteTask;

import java.util.Arrays;
import java.util.List;

public class Eternal extends JavaPlugin {
    
    public static final List<Cooldown> COOLDOWN_LIST = Lists.newArrayList();

    @Override
    public void onEnable() {
        Arrays.asList(
                "§a======================================", "",
                "§a               Eternal Lib               ",
                "§a             by flayart Team            ", "",
                "§aPlugin Version » " + getDescription().getVersion(),
                "§aServer Version » " + getServer().getVersion(), "",
                "§a======================================")
                .forEach(s -> Bukkit.getConsoleSender().sendMessage(s));
        
        new CompleteTask().runTaskTimerAsynchronously(this, 0, 20);
    }
}
