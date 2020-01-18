package fr.vylaria.api.account.mute;

import fr.vylaria.api.account.ban.Ban;
import fr.vylaria.api.data.redis.RedisConnection;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RedisMute implements IMute{

    private String KEY = "mute:";

    @Override
    public Mute get(UUID uuid)
    {
        try (Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource())
        {
            Map<String, String> data = new HashMap<>();
            data = jedis.hgetAll(KEY + uuid.toString());
            if (data.size() > 0)
            {
                String reason = data.get("reason");
                Timestamp demuteDate = Timestamp.valueOf(data.get("demuteDate"));
                String bannisher = data.get("bannisher");
                jedis.close();
                return new Mute(uuid, reason, demuteDate, bannisher);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Mute mute)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        Map<String, String> data = new HashMap<String, String>();
        data.put("uuid", mute.getUuid().toString());
        data.put("reason", mute.getReason());
        data.put("demuteDate", mute.getDemuteDate().toString());
        data.put("bannisher", mute.getBannisher());
        jedis.hmset(KEY + mute.getUuid().toString(), data);
        jedis.close();
    }

    @Override
    public void update(Mute mute)
    {
        create(mute);
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
    public void delete(Mute mute)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        jedis.del(KEY + mute.getUuid().toString());
        jedis.close();
    }

}
