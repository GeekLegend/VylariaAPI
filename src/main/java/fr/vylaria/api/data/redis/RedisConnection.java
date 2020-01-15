package fr.vylaria.api.data.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnection
{

	public static RedisConnection instance;
	private JedisPool jedisPool;
	private final RedisCredentials redisCredentials;
	private final int database;

	public RedisConnection(RedisCredentials redisCredentials, int database)
	{
		instance = this;
		this.redisCredentials = redisCredentials;
		this.database = database;
	}

	private JedisPool setupJedis(RedisCredentials redisCredentials, int database)
	{
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		return new JedisPool(jedisPoolConfig, redisCredentials.getIp(), redisCredentials.getPort(), 5000,
				redisCredentials.getPassword(), database, redisCredentials.getClientName());
	}

	public void init()
	{
		if (jedisPool == null)
		{
			jedisPool = setupJedis(redisCredentials, database);
		}
	}

	public void close()
	{
		if (!jedisPool.isClosed())
		{
			jedisPool.close();
		}
	}

	public JedisPool getJedisPool()
	{
		return jedisPool;
	}

	public static RedisConnection getInstance()
	{
		return instance;
	}
}
