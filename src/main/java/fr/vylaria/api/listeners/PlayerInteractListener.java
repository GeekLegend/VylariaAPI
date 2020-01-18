package fr.vylaria.api.listeners;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.settings.RedisSetting;
import fr.vylaria.api.account.settings.Setting;
import fr.vylaria.api.commands.ModCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Action a = e.getAction();

        if (ModCommand.freezePlayers.contains(p)){
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

}
