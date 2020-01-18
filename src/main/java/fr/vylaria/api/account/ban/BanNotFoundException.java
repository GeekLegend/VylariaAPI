package fr.vylaria.api.account.ban;

public class BanNotFoundException extends Exception
{

    public BanNotFoundException()
    {
        super("Ban not found.");
        System.out.println("BanExeption");
    }

}
