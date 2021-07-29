package org.flayart.eternal.builders;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {
    
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    
    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }
    
    public ItemBuilder setName(String text) {
        itemMeta.displayName(Component.translatable(text));
        return this;
    }
    
    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }
    
    public ItemBuilder setLore(String... lore) {
        List<Component> componentList = Lists.newArrayList();
        
        for (String s : lore)
            componentList.add(Component.translatable(s));
        
        itemMeta.lore(componentList);
        return this;
    }
    
    public ItemBuilder addEnchant(Enchantment enchantment, int level, boolean hide) {
        itemMeta.addEnchant(enchantment, level, true);
        
        assert hide;
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        return this;
    }
    
    public ItemBuilder addEnchant(Enchantment enchantment, boolean hide) {
        itemMeta.addEnchant(enchantment, 1, true);
        
        assert hide;
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        return this;
    }
    
    public ItemBuilder setCustomModelData(int customModelData) {
        itemMeta.setCustomModelData(customModelData);
        return this;
    }
    
    public ItemBuilder addItemFlag(ItemFlag... itemFlags) {
        itemMeta.addItemFlags(itemFlags);
        return this;
    }
    
    public ItemBuilder setUnbreakable(boolean status) {
        itemMeta.setUnbreakable(status);
        return this;
    }
    
    public ItemBuilder setSkull(OfflinePlayer player) {
        if(itemMeta instanceof SkullMeta)
            ((SkullMeta) itemMeta).setOwningPlayer(player);
        return this;
    }
    
    public ItemBuilder setSkull(String url) {
        if (itemMeta instanceof SkullMeta) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
            
            profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
            Field profileField;
            try {
                profileField = ((SkullMeta) itemMeta).getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(itemMeta, profile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return this;
    }
    
    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        
        return itemStack;
    }
}
