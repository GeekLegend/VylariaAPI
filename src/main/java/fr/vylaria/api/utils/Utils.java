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

    public static long timeToMiliseconds(String s){
        //Format: <int><unité> -> Exemple: 7j
        //Unités (première lettre en français) -> s, h, j
        String unit = s.substring(s.length() - 1);
        long nb = Long.parseLong(s.substring(0, s.length() - 1));
        if (unit.equalsIgnoreCase("s")){
            return nb*1000;
        }else if (unit.equalsIgnoreCase("m")){
            return nb*60*1000;
        }else if (unit.equalsIgnoreCase("h")){
            return nb*60*60*1000;
        }else if(unit.equalsIgnoreCase("j")){
            return nb*24*60*60*1000;
        }
        return 0;
    }

    public static String milisecondsToTime(long ms){
        if (ms < 1000){
            return "trop petit";
        }else if(ms >= 1000 && ms < (1000*60)){
            return (ms/1000)+"s";
        }else if(ms >= (1000*60) && ms < (1000*60*60)){
            return (ms/1000/60)+"m"+(ms/1000%60)+"s";
        }else if(ms >= (1000*60*60) && ms < (1000*60*60*24)){
            return (ms/1000/60/60)+"h"+(ms/1000/60%60)+"m";
        }else if(ms >= (1000*60*60*24)){
            return (ms/1000/60/60/24)+"j"+(ms/1000/60/60%24)+"h";
        }
        return null;
    }

}
