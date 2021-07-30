package org.flayart.eternal.configurations;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public class FileManager {
    
    private static final Set<FileManager> DATA = Sets.newHashSet();
    
    @Getter private final String name;
    private File file;
    @Getter private FileConfiguration configuration;
    
    public FileManager(JavaPlugin plugin, String name) {
        this.name = name;
        this.file = new File(plugin.getDataFolder(), name + ".yml");
    
        try {
            plugin.saveResource(name + ".yml", file.createNewFile());
        } catch (IOException ignored) {}
        
        
        this.configuration = YamlConfiguration.loadConfiguration(file);
        DATA.add(this);
    }
    
    public FileManager(JavaPlugin plugin, String name, String... subFolders) {
        this.name = name;
        this.file = new File(plugin.getDataFolder() + "\\" + String.join("\\", subFolders), name + ".yml");
        
        try {
            file.createNewFile();
        } catch (IOException ignored) {}
        
        this.configuration = YamlConfiguration.loadConfiguration(file);
        DATA.add(this);
    }
    
    public void save() throws IOException {
        configuration.save(file);
    }
    
    public void load() {
        this.file = new File(file.getAbsolutePath());
        this.configuration = YamlConfiguration.loadConfiguration(file);
    }
    
    public static Optional<FileManager> getFile(String name) {
        return DATA.stream().filter(data -> data.getName().equalsIgnoreCase(name)).findFirst();
    }
}
