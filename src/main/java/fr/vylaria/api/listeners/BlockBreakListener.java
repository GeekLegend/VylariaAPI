package fr.vylaria.api.listeners;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.settings.RedisSetting;
import fr.vylaria.api.account.settings.Setting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();

        RedisSetting redisSetting = VylariaAPI.getInstance().getRedisSetting();
        Setting setting = redisSetting.get(p.getUniqueId());

        if (setting.isModMode()){
            e.setCancelled(true);
        }
    }

}
