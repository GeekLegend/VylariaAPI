package fr.vylaria.api.events;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.account.settings.RedisSetting;
import fr.vylaria.api.account.settings.Setting;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();

        RedisSetting redisSetting = VylariaAPI.getInstance().getRedisSetting();
        Setting setting = redisSetting.get(p.getUniqueId());

        if (setting.isModMode()){
            e.setCancelled(true);
        }
    }

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
