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
    private final Location location;
    private final String text;
    private List<String> players = Lists.newArrayList();

    public static void newHologram(Location location, String... text) {
        if(text.length == 1)
            Eternal.HOLOGRAM_LIST.add(new Hologram(location, text[0]));
        else {
            int i = 1;
            for (String s : text) {
                Hologram hologram = new Hologram(location.add(0, -0.2 * i, 0), s);
                System.out.println(hologram);
                Eternal.HOLOGRAM_LIST.add(hologram);
                i++;
            }
        }
    }



}
