package org.flayart.eternal.inventories.abstractions.interfaces;

import com.google.common.collect.Maps;
import org.flayart.eternal.inventories.abstractions.Menu;

import java.util.Map;
import java.util.UUID;

public interface MenuImpl {
    
    Map<UUID, Menu> OPENED_MENUS = Maps.newConcurrentMap();
    
}
