package org.flayart.eternal.handlers;


import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;

public class RegionHandler {
    
    public String getRegionName(Location location) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager region = container.get(new BukkitWorld(location.getWorld()));
        
        assert region != null;
        return region.getName();
    }
}
