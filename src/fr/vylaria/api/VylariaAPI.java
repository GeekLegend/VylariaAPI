package fr.vylaria.api;

import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.bungeecord.BungeeChannelApi;
import fr.vylaria.api.commands.manager.CommandsManager;
import fr.vylaria.api.data.redis.RedisConnection;
import fr.vylaria.api.data.redis.RedisCredentials;
import fr.vylaria.api.events.manager.EventsManager;
import fr.vylaria.api.inventory.InventoryUtils;
import fr.vylaria.api.socket.SocketConnection;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URISyntaxException;

public class VylariaAPI extends JavaPlugin
{

    public static VylariaAPI instance;

    private RedisConnection redisConnection;
    private RedisAccount redisAccount;

    private CommandsManager commandsManager;
    private EventsManager eventsManager;

    private BungeeChannelApi bungeeChannelApi;

    private InventoryUtils iu;

    private SocketConnection sc;

    @Override
    public void onEnable()
    {
        instance = this;

        redisConnection = new RedisConnection(new RedisCredentials("play.vylaria.eu", 6379, "bJEAc6z8TIuw7kPa", "root"), 0);
        redisConnection.init();
        redisAccount = new RedisAccount();

        commandsManager = new CommandsManager(this);
        commandsManager.register();

        bungeeChannelApi = BungeeChannelApi.of(this);

        iu = new InventoryUtils();

        eventsManager = new EventsManager(this);
        eventsManager.register();

    }

    @Override
    public void onDisable()
    {
        instance = null;
    }

    public static VylariaAPI getInstance()
    {
        return instance;
    }

    public RedisConnection getRedisConnection()
    {
        return redisConnection;
    }

    public RedisAccount getRedisAccount()
    {
        return redisAccount;
    }

    public BungeeChannelApi getBungeeChannelApi()
    {
        return bungeeChannelApi;
    }

    public InventoryUtils getIu() {
        return iu;
    }
}
