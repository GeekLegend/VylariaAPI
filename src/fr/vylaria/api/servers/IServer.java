package fr.vylaria.api.servers;

public interface IServer
{

    Server get(String id) throws ServerNotFoundException;

    void create(Server server) throws ServerNotFoundException;

    void delete(Server server) throws ServerNotFoundException;

    void update(Server server) throws ServerNotFoundException;

}
