package org.flayart.eternal.inventories.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.flayart.eternal.inventories.abstractions.Menu;
import org.flayart.eternal.inventories.buttons.MenuItem;

public class InventoryListener implements Listener {
    
    public InventoryListener(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Menu menu = Menu.OPENED_MENUS.get(player.getUniqueId());
        
        if (menu == null) return;
        
        if (!event.getView().title().equals(Component.translatable(menu.getTitle(player)))) return;
        
        if (event.getSlot() != event.getRawSlot()) return;
        
        if (event.getClick() == ClickType.SWAP_OFFHAND || event.getClick().isShiftClick() || event.getClick().isKeyboardClick() || isNotAllowed(event.getAction())) {
            event.setCancelled(true);
            return;
        }
        
        event.setCancelled(true);
        
        MenuItem item = menu.getItems().get(event.getSlot());
        
        if (item == null) return;
        
        if (item.getEvent() == null) return;
        
        item.run(event);
    }
    
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Menu menu = Menu.OPENED_MENUS.get(player.getUniqueId());
        
        if (menu == null) return;
        
        menu.onClose(player);
        Menu.OPENED_MENUS.remove(player.getUniqueId());
    }
    
    public boolean isNotAllowed(InventoryAction inventoryAction) {
        return inventoryAction == InventoryAction.MOVE_TO_OTHER_INVENTORY ||
                inventoryAction == InventoryAction.HOTBAR_SWAP ||
                inventoryAction == InventoryAction.SWAP_WITH_CURSOR ||
                inventoryAction == InventoryAction.HOTBAR_MOVE_AND_READD;
    }
}
