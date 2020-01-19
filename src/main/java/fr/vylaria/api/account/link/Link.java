package fr.vylaria.api.account.link;

import java.sql.Timestamp;
import java.util.UUID;

public class Link implements Cloneable{

    private UUID uuid;
    private String name;

    public Link(UUID uuid, String name)
    {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
