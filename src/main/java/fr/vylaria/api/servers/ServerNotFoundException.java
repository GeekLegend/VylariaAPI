package fr.vylaria.api.servers;

public class ServerNotFoundException extends Exception
{

    public ServerNotFoundException()
    {
        super("Server not found.");
    }

}
