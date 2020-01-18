package fr.vylaria.api.listeners;

import fr.vylaria.api.commands.ModCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (ModCommand.freezePlayers.contains(p)){
            e.setCancelled(true);
        }
    }

}
