package fr.vylaria.api;

import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.account.ban.RedisBan;
import fr.vylaria.api.account.mute.RedisMute;
import fr.vylaria.api.account.settings.RedisSetting;
import fr.vylaria.api.bungeecord.BungeeChannelApi;
import fr.vylaria.api.commands.manager.CommandsManager;
import fr.vylaria.api.data.redis.RedisConnection;
import fr.vylaria.api.data.redis.RedisCredentials;
import fr.vylaria.api.listeners.manager.EventsManager;
import fr.vylaria.api.inventory.InventoryUtils;
import fr.vylaria.api.inventory.inventories.manager.InventoryManager;
import fr.vylaria.api.socket.SocketConnection;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URISyntaxException;

public class VylariaAPI extends JavaPlugin
{

    public static VylariaAPI instance;

    private RedisConnection redisConnection;
    private RedisAccount redisAccount;
    private RedisSetting redisSetting;
    private RedisBan redisBan;
    private RedisMute redisMute;

    private CommandsManager commandsManager;
    private EventsManager eventsManager;
    private InventoryManager inventoryManager;

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
        redisSetting = new RedisSetting();
        redisBan = new RedisBan();
        redisMute = new RedisMute();

        commandsManager = new CommandsManager(this);
        commandsManager.register();

        bungeeChannelApi = BungeeChannelApi.of(this);

        iu = new InventoryUtils();

        eventsManager = new EventsManager(this);
        eventsManager.register();

        inventoryManager = new InventoryManager(this);
        inventoryManager.register();

        try {
            sc = new SocketConnection("play.vylaria.eu", 8080);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        sc.send("refreshServerStatus", this.getServer().getMotd());

    }

    @Override
    public void onDisable()
    {
        sc.send("refreshServerStatus", this.getServer().getMotd());
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

    public RedisSetting getRedisSetting() {
        return redisSetting;
    }

    public BungeeChannelApi getBungeeChannelApi()
    {
        return bungeeChannelApi;
    }

    public InventoryUtils getIu() {
        return iu;
    }

    public SocketConnection getSocketConnection() {
        return sc;
    }

    public RedisBan getRedisBan() {
        return redisBan;
    }

    public InventoryManager getInventoryManager(){
        return inventoryManager;
    }

    public RedisMute getRedisMute() {
        return redisMute;
    }
}
