package org.flayart.eternal.database.objects;

import lombok.Data;

@Data
public class Credentials {
    
    private final String hostname;
    private final String database;
    private final String username;
    private final String password;
    private final boolean useSSL;

}
