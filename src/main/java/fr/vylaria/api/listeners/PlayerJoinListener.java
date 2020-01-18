package fr.vylaria.api.listeners;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.account.settings.RedisSetting;
import fr.vylaria.api.account.settings.Setting;
import fr.vylaria.api.commands.ModCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

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

}
