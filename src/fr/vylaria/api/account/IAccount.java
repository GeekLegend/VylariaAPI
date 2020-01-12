package fr.vylaria.api.account;

import java.util.UUID;

public interface IAccount
{
	
	Account get(UUID uuid) throws AccountNoFoundException;
	
	void create(Account account) throws AccountNoFoundException;
	
	void delete(Account account) throws AccountNoFoundException;
	
	void update(Account account) throws AccountNoFoundException;

}
