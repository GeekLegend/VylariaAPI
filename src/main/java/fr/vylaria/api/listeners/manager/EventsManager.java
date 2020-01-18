package fr.vylaria.api.listeners.manager;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.interfaces.IManager;
import fr.vylaria.api.listeners.*;

public class EventsManager implements IManager {

    private VylariaAPI instance;

    public EventsManager(VylariaAPI instance)
    {
        this.instance = instance;
    }

    @Override
    public void register() {
        instance.getServer().getPluginManager().registerEvents(new PlayerInteractEntityListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new PlayerInteractEntityListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new BlockBreakListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new PlayerChatListener(), instance);
    }
}
