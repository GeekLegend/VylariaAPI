package fr.vylaria.api.events;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.RedisAccount;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerEvent implements Listener {

    private List<Player> vanishedPlayers = new ArrayList<Player>();
    private List<Player> freezePlayers = new ArrayList<Player>();

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEntityEvent e){
        if (!(e.getRightClicked() instanceof Player)) return;
        Player p = e.getPlayer();
        Player target = (Player) e.getRightClicked();

        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account account = redisAccount.get(p.getUniqueId());

        if (account.isModMode()){
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
        VylariaAPI.getInstance().getSocketConnection().send("newPlayer", Bukkit.getMotd());
    }

}
