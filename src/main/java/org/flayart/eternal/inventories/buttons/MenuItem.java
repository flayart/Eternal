package org.flayart.eternal.inventories.buttons;

import lombok.Getter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Getter
public class MenuItem {
    
    private final ItemStack itemStack;
    private final Consumer<InventoryClickEvent> event;
    
    protected MenuItem(ItemStack itemStack, Consumer<InventoryClickEvent> event) {
        this.itemStack = itemStack;
        this.event = event;
    }
    
    public static MenuItemBuilder newItem() {
        return new MenuItemBuilder();
    }
    
    public void run(InventoryClickEvent event) {
        this.event.accept(event);
    }
    
    public static final class MenuItemBuilder {
        
        private ItemStack itemStack;
        private Consumer<InventoryClickEvent> event;
        
        public MenuItemBuilder setItem(ItemStack itemStack) {
            this.itemStack = itemStack;
            return this;
        }
        
        public MenuItemBuilder onClick(Consumer<InventoryClickEvent> event) {
            this.event = event;
            return this;
        }
        
        public MenuItem build() {
            return new MenuItem(itemStack, event);
        }
    }
}
