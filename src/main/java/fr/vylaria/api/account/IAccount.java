package fr.vylaria.api.account;

import java.util.UUID;

public interface IAccount
{
	
	Account get(UUID uuid) throws AccountNotFoundException;
	
	void create(Account account) throws AccountNotFoundException;
	
	void delete(Account account) throws AccountNotFoundException;
	
	void update(Account account) throws AccountNotFoundException;

}
