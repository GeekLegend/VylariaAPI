package fr.vylaria.api.inventory.inventories.manager;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.interfaces.IManager;
import fr.vylaria.api.inventory.inventories.SanctionApplyInventory;
import fr.vylaria.api.inventory.inventories.SanctionInventory;
import fr.vylaria.api.inventory.inventories.SanctionMessagesInventory;
import org.bukkit.plugin.PluginManager;

public class InventoryManager implements IManager {

    private VylariaAPI instance;

    private SanctionInventory sanctionInventory;
    private SanctionMessagesInventory sanctionMessagesInventory;
    private SanctionApplyInventory sanctionApplyInventory;

    public InventoryManager(VylariaAPI instance){
        this.instance = instance;
        this.sanctionInventory = new SanctionInventory();
        this.sanctionMessagesInventory = new SanctionMessagesInventory();
        this.sanctionApplyInventory = new SanctionApplyInventory();
    }

    @Override
    public void register() {
        PluginManager pluginManager = instance.getServer().getPluginManager();
        pluginManager.registerEvents(sanctionInventory, instance);
        pluginManager.registerEvents(sanctionMessagesInventory, instance);
        pluginManager.registerEvents(sanctionApplyInventory, instance);
    }

    public SanctionInventory getSanctionInventory(){
        return this.sanctionInventory;
    }

    public SanctionMessagesInventory getSanctionMessagesInventory(){
        return this.sanctionMessagesInventory;
    }

    public SanctionApplyInventory getSanctionApplyInventory(){
        return this.sanctionApplyInventory;
    }

}
