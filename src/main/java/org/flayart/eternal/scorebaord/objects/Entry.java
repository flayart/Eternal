package org.flayart.eternal.scorebaord.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;

@AllArgsConstructor
@Getter
public class Entry {
    
    
    private String prefiix;
    private String suffix;
    
    public static Entry set(String entry) {
        if (entry.length() > 16) {
            boolean fix = entry.toCharArray()[15] == ChatColor.COLOR_CHAR;
            
            String prefix = fix ? entry.substring(0, 15) : entry.substring(0, 16);
            String suffix = fix ? entry.substring(15, entry.length()) : ChatColor.getLastColors(prefix) + entry.substring(16, entry.length());
            
            if (suffix.length() > 16) {
                suffix = suffix.substring(0, 16);
            }
            
            return new Entry(prefix, suffix);
        }
        
        return new Entry("", "");
    }
}
