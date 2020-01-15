package fr.vylaria.api.account.settings;

import java.util.UUID;

public interface ISetting
{
	
	Setting get(UUID uuid) throws SettingNotFoundException;

	void create(Setting setting) throws SettingNotFoundException;

	void delete(Setting setting) throws SettingNotFoundException;
	
	void update(Setting setting) throws SettingNotFoundException;

}
