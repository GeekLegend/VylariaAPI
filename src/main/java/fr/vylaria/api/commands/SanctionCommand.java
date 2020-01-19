package fr.vylaria.api.commands;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.account.ban.RedisBan;
import fr.vylaria.api.inventory.inventories.SanctionInventory;
import fr.vylaria.api.utils.Constants;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SanctionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        if (args.length != 1){
            sender.sendMessage("§7[§cSS§7] Usage: /ss <joueur>");
            return false;
        }

        Player p = (Player) sender;

        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account account = redisAccount.get(p.getUniqueId());

        if (account.getRank().getPower() >= 70){
            if (Bukkit.getServer().getOfflinePlayer(args[0]).hasPlayedBefore()){
                OfflinePlayer victim = Bukkit.getServer().getOfflinePlayer(args[0]);
                RedisBan redisBan = VylariaAPI.getInstance().getRedisBan();
                p.openInventory(new SanctionInventory(victim.getName()).create(p));
            }else{
                p.sendMessage("§7[§cSS§7] Ce joueur n'existe pas!");
            }
        }else{
            p.sendMessage(Constants.NO_PERMISSION);
        }

        return false;
    }

}
