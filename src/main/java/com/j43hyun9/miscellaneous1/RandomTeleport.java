package com.j43hyun9.miscellaneous1;

import net.kyori.adventure.text.ComponentLike;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;

public class RandomTeleport {
    public static Location randomTeleport(World world, Location spawnpoint, int range) {
        // 플레이어가 이동할 곳의 y좌표 -1 칸이 grass block인지 체크해줘야함.
        // 대게 50~80사이로 검사하면 될듯함.
        Random rd = new Random();

        int x = rd.nextInt(range);
        int z = rd.nextInt(range);
        int y = world.getHighestBlockYAt(x, z);
        Location randspawnpoint = new Location(world, x, y, z);


        if(randspawnpoint.getBlock().getType().equals(Material.GRASS_BLOCK)) {
            Bukkit.getPlayer("J43hyun9").sendMessage(randspawnpoint.toString());
            randspawnpoint.add(0, 1, 0);
            return randspawnpoint;
        }
        if(randspawnpoint.getBlock().getType().equals(Material.AIR)) {
            randomTeleport(world, spawnpoint, range);
            Bukkit.getPlayer("J43hyun9").sendMessage("AIR");

        }
        if(randspawnpoint.getBlock().getType().equals(Material.WATER)) {
            randomTeleport(world, spawnpoint, range);
            Bukkit.getPlayer("J43hyun9").sendMessage("WATER");
        }

        return randspawnpoint;
    }
}
