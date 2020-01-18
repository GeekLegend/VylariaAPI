package fr.vylaria.api.account.mute;

import java.sql.Timestamp;
import java.util.UUID;

public class Mute implements Cloneable {

    private UUID uuid;
    private String reason;
    private Timestamp demuteDate;
    private String bannisher;

    public Mute(UUID uuid, String reason, Timestamp demuteDate, String bannisher)
    {
        this.uuid = uuid;
        this.reason = reason;
        this.demuteDate = demuteDate;
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

    public Timestamp getDemuteDate() {
        return demuteDate;
    }

    public void setDemuteDate(Timestamp demuteDate) {
        this.demuteDate = demuteDate;
    }

    public String getBannisher() {
        return bannisher;
    }

    public void setBannisher(String bannisher) {
        this.bannisher = bannisher;
    }

}
