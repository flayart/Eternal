package org.flayart.eternal.holograms;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.Getter;
import org.bukkit.Location;
import org.flayart.eternal.Eternal;

import java.util.List;

@Data
@Getter
public class Hologram {
    private final String name;
    private final Location location;
    private final String text;
    private List<String> players = Lists.newArrayList();

    public static void newHologram(String name, Location location, String... text) {
        if(text.length == 1)
            Eternal.HOLOGRAM_LIST.add(new Hologram(name, location, text[0]));
        else {
            for(String string : text)
                Eternal.HOLOGRAM_LIST.add(new Hologram(name, location.add(0, -0.20, 0), string));
        }
    }



}
