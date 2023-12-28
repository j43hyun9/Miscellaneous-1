package com.j43hyun9.miscellaneous1.event;

import com.j43hyun9.miscellaneous1.RandomTeleport;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.kyori.adventure.text.ComponentLike;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ClickEvent implements Listener {

    Plugin main;
    final String NPC_ALLAY = "알레이";
    World world = Bukkit.getWorld("world");
    String ALLAY_MESSAGE = "§b§l알레이→ 0x3175fb╋#337bf1┼#3581e7╂#3787dd┿#398dd3┼ #3b93c9┼#3d98bf╂#3f9eb5┤#41a4ab┣#43aaa1┿#45b097.#47b68d.#49bc83╋#4bc279┼#4dc86f╂#4fce65┿#51d45b┼ #53da51┼#55df47╂#57e53d┤#59eb33┣#5bf129┿#5df71f.#5ffd15.";
    Location spawnloc = world.getSpawnLocation();
    UUID ALLAY_FOREVILL_TO_FORE = UUID.fromString("2360b0fe-8378-4b58-a7db-9876b8242ff4");

    public ClickEvent(Plugin plugin) {
        this.main = plugin;
    }

    HashMap<UUID, UUID> request = new HashMap<>();
    @EventHandler
    public void onAllayRightClick(NPCRightClickEvent e) { // 알레이는 지역이동NPC임 따라서 if문으로 월드명을 확인해서 마을간 이동을 확인해야함.

        NPC npc = e.getNPC();
        Player p = e.getClicker();

        if(request.containsKey(p.getUniqueId())) {
            e.isCancelled();
        }

        if(npc.getName().equals(NPC_ALLAY)) {
            request.put(p.getUniqueId(), npc.getUniqueId());
            if(npc.getUniqueId().equals(ALLAY_FOREVILL_TO_FORE)) {
                p.playSound(p, Sound.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM, 1f, 1f );
                p.teleport(RandomTeleport.randomTeleport(world, spawnloc, 1000));
                p.playSound(p, Sound.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM, 1f, 1f);
                request.remove(p.getUniqueId());
            }


        }
    }




}
