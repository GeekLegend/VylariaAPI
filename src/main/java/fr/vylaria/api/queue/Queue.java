package fr.vylaria.api.queue;

import org.bukkit.entity.Player;

import java.util.LinkedList;

public class Queue
{

    private String serverName;
    private int position;
    private LinkedList<Player> players;

    public Queue(String serverName)
    {
        this.serverName = serverName;
        this.players = new LinkedList<Player>();
    }

    public void add(Player player)
    {
        players.add(player);
        position++;
    }

    public void remove(Player player)
    {
        players.remove(player);
        position = 0;
    }

    public String getServerName()
    {
        return serverName;
    }

    public int getPosition()
    {
        return position;
    }
}
