package org.flayart.eternal.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketListener;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

@UtilityClass
public class PacketUtils {

    public void sendPacket(Player player, Packet<? extends PacketListener>... packets) {
        for(Packet<? extends PacketListener> packet : packets)
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }
}
