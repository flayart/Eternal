package org.flayart.eternal.holograms.tasks;

import net.kyori.adventure.text.Component;
import net.minecraft.server.v1_16_R3.EntityArmorStand;
import net.minecraft.server.v1_16_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_16_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_16_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.flayart.eternal.Eternal;
import org.flayart.eternal.holograms.Hologram;
import org.flayart.eternal.utils.PacketUtils;

public class HologramTask extends BukkitRunnable {
    @Override
    public void run() {
        for(Hologram hologram : Eternal.HOLOGRAM_LIST) {
            final Location loc = hologram.getLocation();

            for(Player player : Bukkit.getOnlinePlayers()) {

                if(hologram.getPlayers().contains(player.getName())) continue;

                EntityArmorStand stand = new EntityArmorStand((World) loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
                stand.setCustomName(ChatSerializer.a(hologram.getName()));
                stand.setCustomNameVisible(true);
                stand.setMarker(true);
                stand.setNoGravity(true);
                stand.setInvisible(true);

                PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(stand);
                PacketPlayOutEntityMetadata data = new PacketPlayOutEntityMetadata(stand.getId(), stand.getDataWatcher(), false);

                PacketUtils.sendPacket(player, packet, data);
                hologram.getPlayers().add(player.getName());
            }
        }
    }
}
