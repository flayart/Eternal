package org.flayart.eternal.handlers;


import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;

import java.util.Set;

public class RegionHandler {
    
    public Set<ProtectedRegion> getRegions(Location location) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        ApplicableRegionSet regions = container.get(BukkitAdapter.adapt(location.getWorld())).getApplicableRegions(BukkitAdapter.asBlockVector(location));
    
        return regions.getRegions();
    }
    
    public ProtectedRegion getRegion(Location location) {
        for (ProtectedRegion region : getRegions(location)) {
            if (region == null) break;
            
            return region;
        }
        
        return null;
    }
    
    public String getRegionName(Location location) {
        return getRegion(location).getId();
    }
}
