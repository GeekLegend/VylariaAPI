package fr.vylaria.api.listeners;

import fr.vylaria.api.VylariaAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerLeft(PlayerQuitEvent e){
        VylariaAPI.getInstance().getSocketConnection().send("playerLeft", Bukkit.getMotd());
    }

}
