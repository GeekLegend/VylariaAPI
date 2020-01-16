package fr.vylaria.api.account.settings;

import java.util.UUID;

public class Setting implements Cloneable
{

    private UUID uuid;
    private boolean isVanishEnabled;
    private boolean isSpeedEnabled;
    private boolean isModMode;
    private boolean isModVanish;


    public Setting(UUID uuid, boolean isVanishEnabled, boolean isSpeedEnabled, boolean isModMode, boolean isModVanish)
    {
        this.uuid = uuid;
        this.isVanishEnabled = isVanishEnabled;
        this.isSpeedEnabled = isSpeedEnabled;
        this.isModMode = isModMode;
        this.isModVanish = isModVanish;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }

    public boolean isVanishEnabled() {
        return isVanishEnabled;
    }

    public void setVanishEnabled(boolean vanishEnabled) {
        isVanishEnabled = vanishEnabled;
    }

    public boolean isSpeedEnabled() {
        return isSpeedEnabled;
    }

    public void setSpeedEnabled(boolean speedEnabled) {
        isSpeedEnabled = speedEnabled;
    }

    public boolean isModMode() {
        return isModMode;
    }

    public void setModMode(boolean modMode) {
        isModMode = modMode;
    }

    public boolean isModVanish() {
        return isModVanish;
    }

    public void setModVanish(boolean modVanish) {
        isModVanish = modVanish;
    }
}
