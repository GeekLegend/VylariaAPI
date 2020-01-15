package fr.vylaria.api.commands.manager;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.commands.*;
import fr.vylaria.api.interfaces.IManager;

public class CommandsManager implements IManager
{

    private VylariaAPI instance;

    public CommandsManager(VylariaAPI instance)
    {
        this.instance = instance;
    }

    @Override
    public void register()
    {
        instance.getCommand("rank").setExecutor(new RankCommand());
        instance.getCommand("host").setExecutor(new HostCommand());
        instance.getCommand("coins").setExecutor(new CoinCommand());
        instance.getCommand("eur").setExecutor(new EurCommand());
        instance.getCommand("mod").setExecutor(new ModCommand());
    }
}
