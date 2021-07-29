package org.flayart.eternal.database.objects;

import org.flayart.eternal.database.ConnectionPoolManager;

public class SQLManager {
    private ConnectionPoolManager pool;

    public SQLManager(ConnectionPoolManager pool) {
        this.pool = pool;
    }

}
