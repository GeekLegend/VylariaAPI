package fr.vylaria.api.servers;

import fr.vylaria.api.data.redis.RedisConnection;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class RedisServer implements IServer
{

    private String KEY = "server:";

    @Override
    public Server get(String id)
    {
        try (Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource())
        {
            Map<String, String> data = new HashMap<String, String>();
            data = jedis.hgetAll(KEY + id);
            if (data.size() > 0)
            {
                String name = data.get("name");
                int online = Integer.valueOf(data.get("online"));
                ServerStatus status = ServerStatus.valueOf(data.get("status"));
                jedis.close();
                return new Server(id, name, online, status);
            }
        }
        return null;
    }

    @Override
    public void create(Server server)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        Map<String, String> data = new HashMap<String, String>();
        data.put("id", server.getId());
        data.put("name", server.getName());
        data.put("online", Integer.toString(server.getOnline()));
        data.put("status", String.valueOf(server.getServerStatus()));
        jedis.hmset(KEY + server.getId(), data);
        jedis.close();
    }

    @Override
    public void delete(Server server)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        jedis.del(KEY + server.getId());
        jedis.close();
    }

    @Override
    public void update(Server server)
    {
        create(server);
    }
}
