package fr.vylaria.api.listeners;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.settings.RedisSetting;
import fr.vylaria.api.account.settings.Setting;
import fr.vylaria.api.commands.ModCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEntityEvent e){
        if (!(e.getRightClicked() instanceof Player)) return;
        Player p = e.getPlayer();
        Player target = (Player) e.getRightClicked();

        RedisSetting redisSetting = VylariaAPI.getInstance().getRedisSetting();
        Setting setting = redisSetting.get(p.getUniqueId());

        if (setting.isModMode()){
            if (p.getItemInHand().getType() == Material.ICE){
                if (!ModCommand.freezePlayers.contains(target)){
                    ModCommand.freezePlayers.add(target);
                    p.sendMessage("§7[§dMod§7] Vous avez freeze §d§n" + target.getDisplayName() + "§r§7!");
                }else{
                    ModCommand.freezePlayers.remove(target);
                    p.sendMessage("§7[§dMod§7] Vous avez unfreeze §d§n" + target.getDisplayName() + "§r§7!");
                }
            }
        }
    }

}
