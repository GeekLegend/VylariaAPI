package fr.vylaria.api.account;

public class AccountNotFoundException extends Exception
{

    public AccountNotFoundException()
    {
        super("Account not found.");
    }

}
