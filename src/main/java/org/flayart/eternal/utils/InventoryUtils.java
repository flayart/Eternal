package org.flayart.eternal.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.util.Base64;

@UtilityClass
public class InventoryUtils {
    
    public boolean hasEmptySlot(Inventory inventory) {
        return inventory.firstEmpty() != -1;
    }
    
    public void removeMaterial(Inventory inventory, Material material, int amount) {
        for (ItemStack itemStack : inventory.getContents()) {
            
            if (itemStack == null) continue;
            if (itemStack.getType() != material) continue;
            
            if (itemStack.getAmount() == amount)
                inventory.removeItem(itemStack);
            else
                itemStack.setAmount(itemStack.getAmount() - amount);
        }
    }
    
    public int getAmount(Inventory inventory, Material material) {
        int total = 0;
        
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack == null) continue;
            if (itemStack.getType() != material) continue;
            
            total += itemStack.getAmount();
        }
        
        return total;
    }
    
    public String serializeItem(ItemStack itemStack) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
            bukkitObjectOutputStream.writeObject(itemStack);
            bukkitObjectOutputStream.flush();
            
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            return "";
        }
    }
    
    public ItemStack deserializeItem(String string) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(string));
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);
            
            return (ItemStack) bukkitObjectInputStream.readObject();
        } catch (Exception e) {
            return new ItemStack(Material.AIR);
        }
    }
}
