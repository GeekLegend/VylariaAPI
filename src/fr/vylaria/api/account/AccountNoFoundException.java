package fr.vylaria.api.account;

public class AccountNoFoundException extends Exception
{

    public AccountNoFoundException()
    {
        super("Account not found.");
    }

}
