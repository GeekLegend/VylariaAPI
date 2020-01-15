package fr.vylaria.api.data.redis;

public class RedisCredentials
{

	private String ip;
	private int port;
	private String password;
	private String clientName;

	public RedisCredentials(String ip, int port, String password, String clientName)
	{
		this.ip = ip;
		this.port = port;
		this.password = password;
		this.clientName = clientName;
	}

	public String getIp()
	{
		return ip;
	}

	public int getPort()
	{
		return port;
	}

	public String getPassword()
	{
		return password;
	}

	public String getClientName()
	{
		return clientName;
	}

}
