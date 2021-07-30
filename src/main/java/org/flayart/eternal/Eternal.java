package org.flayart.eternal;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Eternal extends JavaPlugin {

    @Override
    public void onEnable() {
        Arrays.asList(
                "§a======================================", "",
                "§a               Eternal Lib               ",
                "§a              by flayart Team            ", "",
                "§aPlugin Version » " + getDescription().getVersion(),
                "§aServer Version » " + getServer().getVersion(), "",
                "§a======================================")
                .forEach(s -> Bukkit.getConsoleSender().sendMessage(s));
    }
}
