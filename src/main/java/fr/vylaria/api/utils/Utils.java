package fr.vylaria.api.utils;

public class Utils
{

    public static boolean isInt(String string)
    {
        try
        {
            Integer.parseInt(string);

            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }

    public static boolean isFloat(String string)
    {
        try
        {
            Float.parseFloat(string);

            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }

}
