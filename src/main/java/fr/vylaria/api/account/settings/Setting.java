package fr.vylaria.api.account.settings;

import java.util.UUID;

public class Setting implements Cloneable
{

    private UUID uuid;
    private boolean isEnabled;

    public Setting(UUID uuid, boolean isEnabled)
    {
        this.uuid = uuid;
        this.isEnabled = isEnabled;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }

    public boolean isEnabled()
    {
        return isEnabled;
    }

    public void setEnabled(boolean enabled)
    {
        isEnabled = enabled;
    }
}
