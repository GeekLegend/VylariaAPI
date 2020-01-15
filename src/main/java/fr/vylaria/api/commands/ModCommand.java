package fr.vylaria.api.commands;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.AccountNoFoundException;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.inventory.InventoryUtils;
import fr.vylaria.api.utils.Constants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                VylariaAPI.getInstance().getIu().save(p);

                p.sendMessage("§7[§dMod§7] Le mode modérateur à été activé!");

                //Set dans l'inventaire
                p.getInventory().setItem(0, new ItemBuilder(Material.IRON_HOE).setName(ChatColor.BLUE + "Vanish").toItemStack());
                p.getInventory().setItem(1, new ItemBuilder(Material.ICE).setName(ChatColor.AQUA + "Freeze").toItemStack());
                p.getInventory().setItem(2, new ItemBuilder(Material.STICK).setName(ChatColor.RED + "Knockback II").addEnchant(Enchantment.KNOCKBACK, 2).toItemStack());
                p.getInventory().setItem(8, new ItemBuilder(Material.WOOD_DOOR).setName(ChatColor.DARK_RED + "Quitter").toItemStack());

                account.setModMode(true);
                redisAccount.update(account);
            }else{
                //Enlever le mode mod

                p.sendMessage("§7[§dMod§7] Le mode modérateur à été désactivé!");

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
