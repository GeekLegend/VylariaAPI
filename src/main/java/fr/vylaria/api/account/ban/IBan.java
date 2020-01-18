package fr.vylaria.api.account.ban;

import fr.vylaria.api.account.settings.Setting;

import java.util.UUID;

public interface IBan {
    Ban get(UUID uuid) throws BanNotFoundException;

    void create(Ban ban) throws BanNotFoundException;

    void delete(Ban ban) throws BanNotFoundException;

    void update(Ban ban) throws BanNotFoundException;

    boolean exists(UUID uuid) throws BanNotFoundException;
}
