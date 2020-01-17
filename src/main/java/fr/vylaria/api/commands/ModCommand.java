package fr.vylaria.api.commands;

import com.sun.deploy.net.MessageHeader;
import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.AccountNoFoundException;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.account.settings.RedisSetting;
import fr.vylaria.api.account.settings.Setting;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.inventory.InventoryUtils;
import fr.vylaria.api.utils.Constants;
import org.bukkit.Bukkit;
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

    public static List<Player> vanishedPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player p = (Player) sender;

        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account account = redisAccount.get(p.getUniqueId());

        RedisSetting redisSetting = VylariaAPI.getInstance().getRedisSetting();
        Setting setting = redisSetting.get(p.getUniqueId());

        Rank playerRank = account.getRank();

        if (playerRank.getPower() >= Rank.MODERATOR.getPower()){

            modMode(p, redisSetting, setting);

        }else{
            p.sendMessage(Constants.NO_PERMISSION);
        }

        return false;
    }

    public static void turnOnModMode(Player p){
        //Metre en mode Mod
        VylariaAPI.getInstance().getIu().save(p);

        //Set dans l'inventaire
        p.getInventory().setItem(0, new ItemBuilder(Material.IRON_HOE).setName(ChatColor.BLUE + "Vanish").toItemStack());
        p.getInventory().setItem(1, new ItemBuilder(Material.STONE_HOE).setName(ChatColor.BLUE + "Vanish (5 ticks)").addEnchant(Enchantment.THORNS, 1).toItemStack());
        p.getInventory().setItem(2, new ItemBuilder(Material.ICE).setName(ChatColor.AQUA + "Freeze").toItemStack());
        p.getInventory().setItem(3, new ItemBuilder(Material.STICK).setName(ChatColor.RED + "Knockback II").addEnchant(Enchantment.KNOCKBACK, 2).toItemStack());
        p.getInventory().setItem(8, new ItemBuilder(Material.WOOD_DOOR).setName(ChatColor.DARK_RED + "Quitter").toItemStack());

        p.setAllowFlight(true);
    }

    public static void modMode(Player p, RedisSetting redisSetting, Setting setting){
        if (!setting.isModMode()){
            turnOnModMode(p);

            p.sendMessage("§7[§dMod§7] Le mode modérateur à été activé!");

            setting.setModMode(true);
            redisSetting.update(setting);
        }else{
            p.sendMessage("§7[§dMod§7] Le mode modérateur à été désactivé!");
            p.setAllowFlight(false);

            setting.setModMode(false);
            redisSetting.update(setting);
            VylariaAPI.getInstance().getIu().restore(p);
        }
    }

    public static void vanishPlayer(Player p){
        RedisSetting redisSetting = VylariaAPI.getInstance().getRedisSetting();
        Setting setting = redisSetting.get(p.getUniqueId());

        if (!setting.isModVanish()){
            for (Player alls : Bukkit.getOnlinePlayers()){
                RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
                Account account = redisAccount.get(alls.getUniqueId());
                if (account.getRank().getPower() < 70){
                    alls.hidePlayer(p);
                }
            }

            p.sendMessage("§7[§dMod§7] Vous êtes désormais invisible pour les joueurs! (Sauf modo et au dessus)");

            vanishedPlayers.add(p);

            setting.setModVanish(true);
            redisSetting.update(setting);
        }else{
            for (Player alls : Bukkit.getOnlinePlayers()){
                alls.showPlayer(p);
            }

            p.sendMessage("§7[§dMod§7] Vous êtes désormais visible pour les joueurs!");

            vanishedPlayers.remove(p);

            setting.setModVanish(false);
            redisSetting.update(setting);
        }


    }

    public static void vanish5ticksPlayer(Player p){
        for (Player alls : Bukkit.getOnlinePlayers()){
            alls.hidePlayer(p);
        }
        p.sendMessage("§7[§dMod§7] Vanish: §dActivé!");
        Bukkit.getScheduler().scheduleSyncDelayedTask(VylariaAPI.getInstance(), () -> {
            for (Player alls : Bukkit.getOnlinePlayers()){
                alls.showPlayer(p);
            }
            p.sendMessage("§7[§dMod§7] Vanish: §dDésactivé!");
        }, 5L);
    }

}
