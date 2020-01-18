package fr.vylaria.api.inventory.inventories.manager;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.interfaces.IManager;
import fr.vylaria.api.inventory.inventories.SSInventory;
import org.bukkit.plugin.PluginManager;

public class InventoryManager implements IManager {

    private VylariaAPI instance;

    private SSInventory ssInventory;

    public InventoryManager(VylariaAPI instance){
        this.instance = instance;
        this.ssInventory = new SSInventory();
    }

    @Override
    public void register() {
        PluginManager pluginManager = instance.getServer().getPluginManager();
        pluginManager.registerEvents(ssInventory, instance);
    }

}
