package fr.vylaria.api.account.mute;

import fr.vylaria.api.account.ban.Ban;
import fr.vylaria.api.account.ban.BanNotFoundException;

import java.util.UUID;

public interface IMute {
    Mute get(UUID uuid) throws MuteNotFoundException;

    void create(Mute mute) throws MuteNotFoundException;

    void delete(Mute mute) throws MuteNotFoundException;

    void update(Mute mute) throws MuteNotFoundException;

    boolean exists(UUID uuid) throws MuteNotFoundException;
}
