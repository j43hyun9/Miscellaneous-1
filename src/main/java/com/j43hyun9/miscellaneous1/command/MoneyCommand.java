package com.j43hyun9.miscellaneous1.command;

import com.j43hyun9.miscellaneous1.hooks.VaultHook;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MoneyCommand implements CommandExecutor {

    Economy econ = VaultHook.getEconomy();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        // " /돈 "
        if(!(commandSender instanceof Player))
            return false;

        Player p = (Player)commandSender;


        if(args.length == 0) {
            p.sendMessage(String.valueOf(econ.getBalance(p.getName())));
        }


        return false;
    }
}
