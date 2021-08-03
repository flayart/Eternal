package org.flayart.eternal.holograms.tasks;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.flayart.eternal.Eternal;
import org.flayart.eternal.holograms.Hologram;
import org.flayart.eternal.utils.PacketUtils;

public class HologramTask extends BukkitRunnable {
    public EntityArmorStand stand;
    public PacketPlayOutSpawnEntityLiving packet;
    public PacketPlayOutEntityMetadata data;

    @Override
    public void run() {
        for(Hologram hologram : Eternal.HOLOGRAM_LIST) {
            if(Eternal.HOLOGRAM_LIST.isEmpty()) continue;
            Location loc = hologram.getLocation();


            for(Player player : Bukkit.getOnlinePlayers()) {
                this.stand = new EntityArmorStand(((CraftWorld) loc.getWorld()).getHandle(), loc.getX(), loc.getY(), loc.getZ());
                stand.setCustomName(IChatBaseComponent.ChatSerializer.a("{\"text\":\""+ hologram.getText() +"\"}"));
                stand.setCustomNameVisible(true);
                stand.setMarker(true);
                stand.setNoGravity(true);
                stand.setInvisible(true);

                this.packet = new PacketPlayOutSpawnEntityLiving(stand);
                this.data = new PacketPlayOutEntityMetadata(stand.getId(), stand.getDataWatcher(), false);
                if(!hologram.getLocation().getWorld().equals(player.getWorld())) continue;
                if(hologram.getLocation().distance(player.getLocation()) > 100) continue;

                if(hologram.getPlayers().containsKey(player.getName())) {
                    update(player, stand, hologram);
                    continue;
                }

                PacketUtils.sendPacket(player, packet, data);
                hologram.getPlayers().put(player.getName(), stand.getId());
            }
        }
    }

    public void update(Player player, EntityArmorStand stand, Hologram hologram) {
        stand.setCustomName(IChatBaseComponent.ChatSerializer.a("{\"text\":\""+ "MAMMT" +"\"}"));
        this.data = new PacketPlayOutEntityMetadata(hologram.getID(player, 0), stand.getDataWatcher(), false);
        PacketUtils.sendPacket(player, data);
        System.out.println(hologram.getID(player, 0));
    }
}
