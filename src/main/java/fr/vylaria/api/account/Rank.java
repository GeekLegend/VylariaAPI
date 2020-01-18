package fr.vylaria.api.account;

public enum Rank
{
	
	ADMIN(130, "Admin","[Admin] ", "§4"),
	RESP_DEV(120, "Resp.Dev","[Resp.Dev] ", "§c"),
	RESP_BUILD(110, "Resp.Build","[Resp.Build] ", "§9"),
	RESP_MOD(100, "Resp.Mod","[Resp.Mod] ", "§6"),
	DEVELOPER(90, "Développeur","[Développeur] ", "§c"),
	STAFF(80, "Staff","[Staff] ", "§9"),
	MODERATOR(70, "Modérateur","[Modérateur] ", "§6"),
	BUILDER(60, "Builder","[Builder] ", "§9"),
	HELPER(50, "Guide","[Guide] ", "§2"),
	PARTNER(40, "Paretanire","[Partenaire] ", "§d"),
	YOUTUBER(30,"Youtubeur", "[Youtubeur] ", "§5"),
	LEGEND(20, "¨Légende","[Légende] ", "§3"),
	VIP(10, "VIP","[VIP] ", "§e"),
	PLAYER(0, "Joueur","[Joueur] ", "§7");

	private int power;
	private String name;
	private String prefix;
	private String color;

	Rank(int power, String name, String prefix, String color)
	{
		this.power = power;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
