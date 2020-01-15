package fr.vylaria.api.account;

public enum Rank
{
	
	ADMIN(130, "[Admin] ", "§4"),
	RESP_DEV(120, "[Resp.Dev] ", "§c"),
	RESP_BUILD(110, "[Resp.Build] ", "§9"),
	RESP_MOD(100, "[Resp.Mod] ", "§6"),
	DEVELOPER(90, "[Développeur] ", "§c"),
	STAFF(80, "[Staff] ", "§9"),
	MODERATOR(70, "[Modérateur] ", "§6"),
	BUILDER(60, "[Builder] ", "§9"),
	HELPER(50, "[Guide] ", "§2"),
	PARTNER(40, "[Partenaire] ", "§d"),
	YOUTUBER(30, "[Youtubeur] ", "§5"),
	LEGEND(20, "[Légende] ", "§3"),
	VIP(10, "[VIP] ", "§e"),
	PLAYER(0, "[Joueur] ", "§7");

	private int power;
	private String prefix;
	private String color;

	Rank(int power, String prefix, String color)
	{
		this.power = power;
		this.prefix = prefix;
		this.color = color;
	}

	public static Rank getByPrefix(String prefix)
	{
		for (Rank rank : values())
		{
			if (rank.getPrefix().equals(prefix))
			{
				return rank;
			}
		}
		return null;
	}
	
	public static Rank getByPower(int power)
	{
		for (Rank rank : values())
		{
			if (rank.getPower() == power)
			{
				return rank;
			}
		}
		return null;
	}

	public int getPower()
	{
		return power;
	}

	public void setPower(int power)
	{
		this.power = power;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

}
