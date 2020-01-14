package fr.vylaria.api.account.settings;

public class SettingNotFoundException extends Exception
{

    public SettingNotFoundException()
    {
        super("Setting not found.");
    }

}
