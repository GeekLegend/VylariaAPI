package fr.vylaria.api.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class AbstractInventory
{

    protected Inventory inventory;
    protected int size;
    protected String name;

    public AbstractInventory(int size, String name)
    {
        this.size = size;
        this.name = name;
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    public abstract Inventory create(Player player);

    public Inventory getInventory()
    {
        return inventory;
    }

    public int getSize()
    {
        return size;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
