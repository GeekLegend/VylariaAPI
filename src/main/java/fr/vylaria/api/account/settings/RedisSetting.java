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
                boolean isVanishEnabled = Boolean.parseBoolean(data.get("isVanishEnabled"));
                boolean isSpeedEnabled = Boolean.parseBoolean(data.get("isSpeedEnabled"));
                boolean isModMode = Boolean.parseBoolean(data.get("isModMode"));
                boolean isModVanish = Boolean.parseBoolean(data.get("isModVanish"));
                jedis.close();
                return new Setting(uuid, isVanishEnabled, isSpeedEnabled, isModMode, isModVanish);
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
        data.put("isVanishEnabled", String.valueOf(setting.isVanishEnabled()));
        data.put("isSpeedEnabled", String.valueOf(setting.isSpeedEnabled()));
        data.put("isModMode", String.valueOf(setting.isModMode()));
        data.put("isModVanish", String.valueOf(setting.isModVanish()));
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
