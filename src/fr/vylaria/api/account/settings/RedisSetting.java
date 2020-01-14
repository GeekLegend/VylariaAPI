package fr.vylaria.api.account.settings;

import fr.vylaria.api.data.redis.RedisConnection;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RedisSetting implements ISetting
{

    private String KEY = "setting:";

    @Override
    public Setting get(UUID uuid)
    {
        try (Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource())
        {
            Map<String, String> data = new HashMap<String, String>();
            data = jedis.hgetAll(KEY + uuid.toString());
            if (data.size() > 0)
            {
                boolean isEnabled = Boolean.valueOf(data.get("isEnabled"));
                jedis.close();
                return new Setting(uuid, isEnabled);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Setting setting)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        Map<String, String> data = new HashMap<String, String>();
        data.put("uuid", setting.getUuid().toString());
        data.put("isEnabled", String.valueOf(setting.isEnabled()));
        jedis.hmset(KEY + setting.getUuid().toString(), data);
        jedis.close();
    }

    @Override
    public void update(Setting setting)
    {
        create(setting);
    }

    @Override
    public void delete(Setting setting)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        jedis.del(KEY + setting.getUuid().toString());
        jedis.close();
    }

}
