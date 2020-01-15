package fr.vylaria.api.servers;

public class Server
{

    private String id;
    private String name;
    private int online;
    private ServerStatus serverStatus;

    public Server(String id, String name, int online, ServerStatus serverStatus)
    {
        this.id = id;
        this.name = name;
        this.online = online;
        this.serverStatus = serverStatus;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getOnline()
    {
        return online;
    }

    public void setOnline(int online)
    {
        this.online = online;
    }

    public ServerStatus getServerStatus()
    {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus)
    {
        this.serverStatus = serverStatus;
    }
}
