package fr.vylaria.api.events.manager;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.events.BlockEvent;
import fr.vylaria.api.events.PlayerEvent;
import fr.vylaria.api.interfaces.IManager;

public class EventsManager implements IManager {

    private VylariaAPI instance;

    public EventsManager(VylariaAPI instance)
    {
        this.instance = instance;
    }

    @Override
    public void register() {
        instance.getServer().getPluginManager().registerEvents(new BlockEvent(), instance);
        instance.getServer().getPluginManager().registerEvents(new PlayerEvent(), instance);
    }
}
