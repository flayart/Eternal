package org.flayart.eternal.database.objects;

import lombok.Data;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Objects;

@Data
public class Credentials {
    
    private final String hostname;
    private final String database;
    private final String username;
    private final String password;
    private final boolean useSSaL;
    
    public Credentials(YamlConfiguration file, String configurationSection) {
        Objects.requireNonNull(file.getConfigurationSection(configurationSection));
        
        this.hostname = file.getString(configurationSection + ".hostname");
        this.database = file.getString(configurationSection + ".database");
        this.username = file.getString(configurationSection + ".username");
        this.password = file.getString(configurationSection + ".password");
        this.useSSL = file.getBoolean(configurationSection + ".useSSL");
    }
    
    public Credentials(YamlConfiguration file, String hostnamePath, String databasePath, String usernamePath, String passwordPath, String useSSLPath) {
        this.hostname = file.getString(hostnamePath);
        this.database = file.getString(databasePath);
        this.username = file.getString(usernamePath);
        this.password = file.getString(passwordPath);
        this.useSSL = file.getBoolean(useSSLPath);
    }
}
