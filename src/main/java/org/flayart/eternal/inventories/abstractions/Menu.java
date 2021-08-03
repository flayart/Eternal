package org.flayart.eternal.inventories.abstractions;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.flayart.eternal.builders.ItemBuilder;
import org.flayart.eternal.inventories.MenuHolder;
import org.flayart.eternal.inventories.abstractions.interfaces.MenuImpl;
import org.flayart.eternal.inventories.buttons.MenuItem;

import java.util.Map;

@Getter
@Setter
public abstract class Menu implements MenuImpl {
    
    private boolean placeholder;
    private Material placeholderMaterial;
    
    public abstract String getTitle(Player player);
    
    public abstract int getSize();
    
    public abstract Map<Integer, MenuItem> getItems();
    
    public void open(Player player) {
        Inventory inventory = null;
        String title = getTitle(player);
        
        if (title.length() > 32)
            title = title.substring(0, 32);
        
        if (OPENED_MENUS.containsKey(player.getUniqueId()) &&
                OPENED_MENUS.get(player.getUniqueId()) != this &&
                player.getOpenInventory().getBottomInventory().getHolder() != null &&
                player.getOpenInventory().getTopInventory().getHolder() instanceof MenuHolder) {
            inventory = player.getOpenInventory().getTopInventory();
        } else {
            player.closeInventory();
        }
        
        if (inventory == null) {
            inventory = Bukkit.createInventory(null, getSize(), Component.translatable(title));
        }
        
        if (isPlaceholder())
            for (int i = 0; i < getSize(); i++) inventory.setItem(i, new ItemBuilder(getPlaceholderMaterial()).setName(" ").build());
        
        for (Map.Entry<Integer, MenuItem> itemEntry : getItems().entrySet()) {
            inventory.setItem(itemEntry.getKey(), itemEntry.getValue().getItemStack());
        }
        
        OPENED_MENUS.put(player.getUniqueId(), this);
        player.openInventory(inventory);
        onOpen(player);
    }
    
    public void onOpen(Player player) {
    
    }
    
    public void onClose(Player player) {
    
    }
}
