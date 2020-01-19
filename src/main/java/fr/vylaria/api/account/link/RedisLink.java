package fr.vylaria.api.account.link;

import fr.vylaria.api.account.mute.Mute;
import fr.vylaria.api.data.redis.RedisConnection;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RedisLink implements ILink {

    private String KEY = "link:";

    @Override
    public Link get(String name)
    {
        try (Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource())
        {
            Map<String, String> data = new HashMap<>();
            data = jedis.hgetAll(KEY + name);
            if (data.size() > 0)
            {
                UUID uuid = UUID.fromString(data.get("name"));
                jedis.close();
                return new Link(uuid, name);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Link link)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        Map<String, String> data = new HashMap<String, String>();
        data.put("uuid", link.getUuid().toString());
        data.put("name", link.getName());
        jedis.hmset(KEY + link.getName(), data);
        jedis.close();
    }

    @Override
    public void update(Link link)
    {
        create(link);
    }

    @Override
    public void delete(Link link)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        jedis.del(KEY + link.getUuid().toString());
        jedis.close();
    }

}
