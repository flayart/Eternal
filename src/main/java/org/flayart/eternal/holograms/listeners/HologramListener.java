package org.flayart.eternal.holograms.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.flayart.eternal.Eternal;
import org.flayart.eternal.holograms.Hologram;

public class HologramListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        for(Hologram hologram : Eternal.HOLOGRAM_LIST)
            hologram.getPlayers().remove(event.getPlayer().getName());
    }
}
