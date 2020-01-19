package fr.vylaria.api.account.link;

public class LinkNotFoundException extends Exception {

    public LinkNotFoundException()
    {
        super("link not found.");
    }

}
