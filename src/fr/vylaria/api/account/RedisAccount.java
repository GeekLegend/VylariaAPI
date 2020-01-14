package fr.vylaria.api.account;

import fr.vylaria.api.data.redis.RedisConnection;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RedisAccount implements IAccount
{

    private String KEY = "account:";

    @Override
    public Account get(UUID uuid)
    {
        try (Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource())
        {
            Map<String, String> data = new HashMap<String, String>();
            data = jedis.hgetAll(KEY + uuid.toString());
            if (data.size() > 0)
            {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date firstJoin = dateFormat.parse(data.get("firstConnection"));
                Date lastJoin = dateFormat.parse(data.get("lastConnection"));
                String currentName = data.get("currentName");
                Timestamp firstConnection = new Timestamp(firstJoin.getTime());
                Timestamp lastConnection = new Timestamp(lastJoin.getTime());
                float coins = Float.parseFloat(data.get("coins"));
                float eur = Float.parseFloat(data.get("eur"));
                float playingTime = Float.parseFloat(data.get("playingTime"));
                String nickName = data.get("nickName");
                String lang = data.get("lang");
                int rank = Integer.valueOf(data.get("rank"));
                boolean isModMode = Boolean.getBoolean(data.get("isModMode"));
                String suffix = data.get("suffix");
                jedis.close();
                return new Account(uuid, currentName, firstConnection, lastConnection, coins, eur, playingTime, nickName, lang, Rank.getByPower(rank), suffix, isModMode);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Account account)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        Map<String, String> data = new HashMap<String, String>();
        data.put("uuid", account.getUuid().toString());
        data.put("currentName", account.getCurrentName());
        data.put("firstConnection", account.getFirstConnection().toString());
        data.put("lastConnection", account.getLastConnection().toString());
        data.put("coins", Float.toString(account.getCoins()));
        data.put("eur", Float.toString(account.getEur()));
        data.put("playingTime", Float.toString(account.getPlayingTime()));
        data.put("nickName", account.getNickName());
        data.put("lang", account.getLang());
        data.put("rank", Integer.toString(account.getRank().getPower()));
        data.put("suffix", account.getSuffix());
        jedis.hmset(KEY + account.getUuid().toString(), data);
        jedis.close();
    }

    @Override
    public void update(Account account)
    {
        create(account);
    }

    @Override
    public void delete(Account account)
    {
        Jedis jedis = RedisConnection.getInstance().getJedisPool().getResource();
        jedis.del(KEY + account.getUuid().toString());
        jedis.close();
    }

}