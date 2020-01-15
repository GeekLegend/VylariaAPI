package fr.vylaria.api.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class InventoryUtils
{

    private Map<Player, ItemStack[]> armorContents;
    private Map<Player, ItemStack[]> contents;

    public InventoryUtils()
    {
        this.armorContents = new HashMap<Player, ItemStack[]>();
        this.contents = new HashMap<Player, ItemStack[]>();
    }

    public void save(Player player)
    {
        armorContents.put(player, player.getInventory().getArmorContents());
        contents.put(player, player.getInventory().getContents());
        
        player.getInventory().clear();
    }

    public void restore(Player player)
    {
        player.getInventory().clear();
        if (contents.containsKey(player)){
            player.getInventory().setArmorContents(armorContents.get(player));
            player.getInventory().setContents(contents.get(player));
            player.updateInventory();
        }
        armorContents.remove(player);
        contents.remove(player);
    }

    public boolean contains(Player player)
    {
        return armorContents.containsKey(player) && contents.containsKey(player);
    }

    public Map<Player, ItemStack[]> getArmorContents()
    {
        return armorContents;
    }

    public Map<Player, ItemStack[]> getContents()
    {
        return contents;
    }

}
