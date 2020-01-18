package fr.vylaria.api.account.mute;

public class MuteNotFoundException extends Exception{

    public MuteNotFoundException()
    {
        super("mute not found.");
    }

}
