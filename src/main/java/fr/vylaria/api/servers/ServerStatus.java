package fr.vylaria.api.servers;

public enum ServerStatus
{

    NO_STATUS("Aucun status", "§c"),
    WAITING("En attente", "§a"),
    IN_GAME("En jeu", "§c"),
    FINISH("Fin", "§c"),
    RESTART("Redémarage", "§c");

    private String name;
    private String nameColor;
    private static ServerStatus serverStatus;

    ServerStatus(String name, String nameColor)
    {
        this.name = name;
        this.nameColor = nameColor;
    }

    public static ServerStatus getServerStatus()
    {
        return serverStatus;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNameColor()
    {
        return nameColor;
    }

    public void setNameColor(String nameColor)
    {
        this.nameColor = nameColor;
    }
}
