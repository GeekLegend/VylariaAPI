package fr.vylaria.api.listeners;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.mute.Mute;
import fr.vylaria.api.account.mute.RedisMute;
import fr.vylaria.api.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.Timestamp;

public class PlayerChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        RedisMute redisMute = VylariaAPI.getInstance().getRedisMute();
        if (redisMute.exists(p.getUniqueId())){
            Mute mute = redisMute.get(p.getUniqueId());
            if (mute.getDemuteDate().compareTo(new Timestamp(System.currentTimeMillis())) > 0){
                Timestamp actual = new Timestamp(System.currentTimeMillis());
                long time = (mute.getDemuteDate().getTime()-actual.getTime());
                p.sendMessage("§cVous êtes mute! Temps restant: " + Utils.milisecondsToTime(time) + " pour " + mute.getReason());
                e.setCancelled(true);
            }else{
                redisMute.delete(mute);
            }
        }else{
            long miliseconds = (System.currentTimeMillis() + Utils.timeToMiliseconds("2h"));
            Timestamp ts = new Timestamp(System.currentTimeMillis() + Utils.timeToMiliseconds("2h"));
            redisMute.create(new Mute(p.getUniqueId(), "test", ts, "Zolwen"));
        }
    }

}
