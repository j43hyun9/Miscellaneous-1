package com.j43hyun9.miscellaneous1;

import com.j43hyun9.miscellaneous1.message.Message;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ActionbarTimeTask {

    Plugin plugin;
    List<Player> lindworld;

    ActionbarTimeTask(Plugin plugin) {
        this.plugin = plugin;
    }

    public void playerActionbarTimeTask() {

        new BukkitRunnable() {
            @Override
            public void run() {

                lindworld = Bukkit.getWorld("lind").getPlayers();
                for(int i=0; i< lindworld.size(); i++) {
                    lindworld.get(i).spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Message.ACTION_BAR));
                }

            }
        }.runTaskTimerAsynchronously(plugin, 40L, 40L);


    }
}
