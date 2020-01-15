package fr.vylaria.api.commands;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.AccountNoFoundException;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.inventory.InventoryUtils;
import fr.vylaria.api.utils.Constants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModCommand implements CommandExecutor {

    List<Player> vanishedPlayers = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player p = (Player) sender;
        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account account = redisAccount.get(p.getUniqueId());
        Rank playerRank = account.getRank();

        if (playerRank.getPower() >= Rank.MODERATOR.getPower()){

            if (!account.isModMode()){
                System.out.printf("cc");
                //Metre en mode Mod
                VylariaAPI.getInstance().getIu().save(p);

                /*//Item 1: VanishItem
                ItemStack vanish = new ItemStack(Material.IRON_HOE);
                ItemMeta vanishIM = vanish.getItemMeta();
                vanishIM.setDisplayName(ChatColor.BLUE + "Vanish");
                vanish.setItemMeta(vanishIM);
                                                                                //A CHANGER
                //Item 1: VanishItem
                ItemStack freeze = new ItemStack(Material.IRON_HOE);
                ItemMeta freezeIM = vanish.getItemMeta();
                freezeIM.setDisplayName(ChatColor.AQUA + "Freeze");
                freeze.setItemMeta(freezeIM);

                //Set dans l'inventaire
                p.getInventory().setItem(0, vanish);
                p.getInventory().setItem(1, freeze);*/

                account.setModMode(true);
                redisAccount.update(account);
                System.out.println(account.isModMode());
            }else{
                System.out.println("ccc");
                //Enlever le mode mod
                account.setModMode(false);
                redisAccount.update(account);
                VylariaAPI.getInstance().getIu().restore(p);
            }

        }else{
            p.sendMessage(Constants.NO_PERMISSION);
        }

        return false;
    }

}
