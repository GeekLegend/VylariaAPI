package fr.vylaria.api.account;

import java.sql.Timestamp;
import java.util.UUID;

public class Account implements Cloneable
{

    private UUID uuid;
    private String currentName;
    private Timestamp firstConnection;
    private Timestamp lastConnection;
    private float coins;
    private float eur;
    private float playingTime;
    private String nickName;
    private String lang;
    private Rank rank;
    private String suffix;
    private boolean isModMode;

    public Account(UUID uuid, String currentName, Timestamp firstConnection, Timestamp lastConnection, float coins, float eur, float playingTime, String nickName, String lang, Rank rank, String suffix, boolean isModMode)
    {
        this.uuid = uuid;
        this.currentName = currentName;
        this.firstConnection = firstConnection;
        this.lastConnection = lastConnection;
        this.coins = coins;
        this.eur = eur;
        this.playingTime = playingTime;
        this.nickName = nickName;
        this.lang = lang;
        this.rank = rank;
        this.suffix = suffix;
        this.isModMode = isModMode;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }

    public String getCurrentName()
    {
        return currentName;
    }

    public void setCurrentName(String currentName)
    {
        this.currentName = currentName;
    }

    public Timestamp getFirstConnection()
    {
        return firstConnection;
    }

    public void setFirstConnection(Timestamp firstConnection)
    {
        this.firstConnection = firstConnection;
    }

    public Timestamp getLastConnection()
    {
        return lastConnection;
    }

    public void setLastConnection(Timestamp lastConnection)
    {
        this.lastConnection = lastConnection;
    }

    public float getCoins()
    {
        return coins;
    }

    public void setCoins(float coins)
    {
        this.coins = coins;
    }

    public float getEur()
    {
        return eur;
    }

    public void setEur(float eur)
    {
        this.eur = eur;
    }

    public float getPlayingTime()
    {
        return playingTime;
    }

    public void setPlayingTime(float playingTime)
    {
        this.playingTime = playingTime;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getLang()
    {
        return lang;
    }

    public void setLang(String lang)
    {
        this.lang = lang;
    }

    public Rank getRank()
    {
        return rank;
    }

    public void setRank(Rank rank)
    {
        this.rank = rank;
    }

	public String getSuffix()
	{
		return suffix;
	}

	public void setSuffix(String suffix) { this.suffix = suffix; }

    public boolean isModMode() {
        return isModMode;
    }

    public void setModMode(boolean modMode) {
        isModMode = modMode;
    }
}
