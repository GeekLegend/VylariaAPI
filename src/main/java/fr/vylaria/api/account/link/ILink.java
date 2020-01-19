package fr.vylaria.api.account.link;

import fr.vylaria.api.account.mute.Mute;
import fr.vylaria.api.account.mute.MuteNotFoundException;

import java.util.UUID;

public interface ILink {

    Link get(String name) throws LinkNotFoundException;

    void create(Link link) throws LinkNotFoundException;

    void delete(Link link) throws LinkNotFoundException;

    void update(Link link) throws LinkNotFoundException;

}
