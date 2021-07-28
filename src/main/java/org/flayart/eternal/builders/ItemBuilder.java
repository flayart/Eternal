package org.flayart.eternal.builders;

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

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
    
    public ItemStack build() {
        return itemStack;
    }
}
