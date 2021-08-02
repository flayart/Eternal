package org.flayart.eternal.inventories;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MenuHolder implements InventoryHolder {
    
    private final OfflinePlayer player;
    
    public MenuHolder(OfflinePlayer player) {
        this.player = player;
    }
    
    @Override
    public @NotNull Inventory getInventory() {
        return Objects.requireNonNull(player.getPlayer()).getOpenInventory().getTopInventory();
    }
}
