package fr.vylaria.api.account.ban;

import fr.vylaria.api.account.settings.Setting;
import fr.vylaria.api.data.redis.RedisConnection;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RedisBan implements IBan{
    private String KEY = "ban:";

    @Override
    public Ban get(UUID uuid)
    {
        try (Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource())
        {
            Map<String, String> data = new HashMap<String, String>();
            data = jedis.hgetAll(KEY + uuid.toString());
            if (data.size() > 0)
            {
                String reason = data.get("reason");
                Timestamp unbanDate = Timestamp.valueOf(data.get("unbanDate"));
                String bannisher = data.get("bannisher");
                jedis.close();
                return new Ban(uuid, reason, unbanDate, bannisher);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Ban ban)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        Map<String, String> data = new HashMap<String, String>();
        data.put("uuid", ban.getUuid().toString());
        data.put("reason", ban.getReason());
        data.put("unbanDate", ban.getDebanDate().toString());
        data.put("bannisher", ban.getBannisher());
        jedis.hmset(KEY + ban.getUuid().toString(), data);
        jedis.close();
    }

    @Override
    public void update(Ban ban)
    {
        create(ban);
    }

    @Override
    public boolean exists(UUID uuid){
        try (Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource())
        {
            Map<String, String> data = new HashMap<String, String>();
            data = jedis.hgetAll(KEY + uuid.toString());
            if (data.size() > 0)
            {
                return true;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(Ban ban)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        jedis.del(KEY + ban.getUuid().toString());
        jedis.close();
    }
}
