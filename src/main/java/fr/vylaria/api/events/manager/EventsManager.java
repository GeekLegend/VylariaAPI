package fr.vylaria.api.events.manager;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.events.BlockListener;
import fr.vylaria.api.events.PlayerListener;
import fr.vylaria.api.interfaces.IManager;

public class EventsManager implements IManager {

    private VylariaAPI instance;

    public EventsManager(VylariaAPI instance)
    {
        this.instance = instance;
    }

    @Override
    public void register() {
        instance.getServer().getPluginManager().registerEvents(new BlockListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new PlayerListener(), instance);
    }
}
