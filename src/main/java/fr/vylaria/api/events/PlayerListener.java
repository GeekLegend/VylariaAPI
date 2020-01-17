package fr.vylaria.api.events;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.account.settings.RedisSetting;
import fr.vylaria.api.account.settings.Setting;
import fr.vylaria.api.commands.ModCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {

    private List<Player> freezePlayers = new ArrayList<>();

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEntityEvent e){
        if (!(e.getRightClicked() instanceof Player)) return;
        Player p = e.getPlayer();
        Player target = (Player) e.getRightClicked();

        RedisSetting redisSetting = VylariaAPI.getInstance().getRedisSetting();
        Setting setting = redisSetting.get(p.getUniqueId());

        if (setting.isModMode()){
            if (p.getItemInHand().getType() == Material.ICE){
                if (!freezePlayers.contains(target)){
                    freezePlayers.add(target);
                    p.sendMessage("§7[§dMod§7] Vous avez freeze §d§n" + target.getDisplayName() + "§r§7!");
                }else{
                    freezePlayers.remove(target);
                    p.sendMessage("§7[§dMod§7] Vous avez unfreeze §d§n" + target.getDisplayName() + "§r§7!");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();

        if (freezePlayers.contains(p)){
            e.setCancelled(true);
            return;
        }

        RedisSetting redisSetting = VylariaAPI.getInstance().getRedisSetting();
        Setting setting = redisSetting.get(p.getUniqueId());

        if ((a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) && setting.isModMode()){
            if (e.getItem().getType() == Material.WOOD_DOOR){
                ModCommand.modMode(p, redisSetting, setting);
                e.setCancelled(true);
            }else if (e.getItem().getType() == Material.IRON_HOE){
                ModCommand.vanishPlayer(p);
            }else if(e.getItem().getType() == Material.STONE_HOE){
                ModCommand.vanish5ticksPlayer(p);
            }
        }else if((a == Action.LEFT_CLICK_BLOCK || a == Action.LEFT_CLICK_AIR) && setting.isModMode() && e.getItem().getType() != Material.STICK){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (freezePlayers.contains(p)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        VylariaAPI.getInstance().getSocketConnection().send("newPlayer", Bukkit.getMotd());

        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account account = redisAccount.get(p.getUniqueId());

        RedisSetting redisSetting = VylariaAPI.getInstance().getRedisSetting();
        Setting setting = redisSetting.get(p.getUniqueId());

        //Met invisible pour tout les joueurs, le joueur qui vient de rejoindre (si il est en vanish)
        if (setting.isModVanish()){
            for (Player alls : Bukkit.getOnlinePlayers()){
                RedisAccount redisAccountAlls = VylariaAPI.getInstance().getRedisAccount();
                Account accountalls = redisAccountAlls.get(alls.getUniqueId());
                if (accountalls.getRank().getPower() < 70){
                    alls.hidePlayer(p);
                }
            }
        }else{
            for (Player alls : Bukkit.getOnlinePlayers()){
                alls.showPlayer(p);
            }
        }

        //Met invisible toutes les personnes vanish sur le serveur
        if (account.getRank().getPower() < 70) {
            for (Player vanishedPlayer : ModCommand.vanishedPlayers) {
                p.hidePlayer(vanishedPlayer);
            }
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(VylariaAPI.getInstance(), () -> {
            if (setting.isModMode()){
                ModCommand.turnOnModMode(p);
            }else{
                p.setAllowFlight(false);
            }
        }, 20L);
    }

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent e){
        VylariaAPI.getInstance().getSocketConnection().send("playerLeft", Bukkit.getMotd());
    }

}
