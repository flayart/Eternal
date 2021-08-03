package org.flayart.eternal.holograms;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.flayart.eternal.Eternal;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Getter
public class Hologram {
    private final String name;
    private final Location location;
    private final String text;
    private ConcurrentMap<String, Integer> players = Maps.newConcurrentMap();

    public static void newHologram(String name, Location location, String... text) {
        if(text.length == 1)
            Eternal.HOLOGRAM_LIST.add(new Hologram(name, location, text[0]));
        else {
            AtomicInteger i = new AtomicInteger(1);
            for (String s : text) {
                Eternal.HOLOGRAM_LIST.add(new Hologram(name, location.clone().add(0, -0.25 * i.getAndIncrement(), 0), s));
            }
        }
    }


    public List<Integer> getIDs(Player player) {
        List<Integer> holograms = Lists.newArrayList();
        players.keySet().forEach(k -> holograms.add(players.get(player.getName())));
        return holograms;
    }

    public int getID(Player player, int i) {
        List<Integer> holograms = Lists.newArrayList();
        players.keySet().forEach(k -> holograms.add(players.get(player.getName())));
        return holograms.get(i);
    }
}
