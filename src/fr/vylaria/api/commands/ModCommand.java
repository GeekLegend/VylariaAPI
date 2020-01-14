package fr.vylaria.api.commands;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.AccountNoFoundException;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.utils.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player p = (Player) sender;
        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account account = redisAccount.get(p.getUniqueId());
        Rank playerRank = account.getRank();

        if (playerRank.getPower() >= Rank.MODERATOR.getPower()){

            if (!account.isModMode()){
                //Metre en mode Mod
                

            }else{
                //Enlever le mode mod
                account.setModMode(false);
                redisAccount.update(account);
            }

        }else{
            p.sendMessage(Constants.NO_PERMISSION);
        }

        return false;
    }

}
