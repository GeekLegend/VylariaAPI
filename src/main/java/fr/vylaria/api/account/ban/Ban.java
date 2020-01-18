package fr.vylaria.api.account.ban;

import java.sql.Timestamp;
import java.util.UUID;

public class Ban implements Cloneable{


    private UUID uuid;
    private String reason;
    private Timestamp debanDate;
    private String bannisher;

    public Ban(UUID uuid, String reason, Timestamp debanDate, String bannisher)
    {
        this.uuid = uuid;
        this.reason = reason;
        this.debanDate = debanDate;
        this.bannisher = bannisher;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getDebanDate() {
        return debanDate;
    }

    public void setDebanDate(Timestamp debanDate) {
        this.debanDate = debanDate;
    }

    public String getBannisher() {
        return bannisher;
    }

    public void setBannisher(String bannisher) {
        this.bannisher = bannisher;
    }
}
