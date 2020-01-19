package fr.vylaria.api.sanctions;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum Sanction {

    USELESS_MESSAGE("§aMessage inutile", "Message inutile", new String[]{"§fMessage qui n'apporte rien à personne", "§fet qui encombre le chat sans raison"," ","§fExemple:","§fqfsegzefqfqf"," ","§b> §fClic gauche pour appliquer","§7Mute 1m"}, SanctionCategory.MESSAGE, Material.SEEDS, 10, SanctionType.MUTE, 1000*60),
    FAKE_INFO("§aFausse information", "Fausse information", new String[]{"§fMessage mensonger, information fausse", " ","§fExemple:","§f\"J'étais modérateur avant\""," ","§b> §fClic gauche pour appliquer","§7Mute 3m"}, SanctionCategory.MESSAGE, Material.IRON_HOE, 11, SanctionType.MUTE, 1000*60*3),
    FLOOD("§aFlood", "Flood", new String[]{"§fEnvoi du même message plusieurs fois","§fen un court interval de temps"," ","§fExemple:","§f\"Venez UHC", "§fVenez UHC", "§fVenez UHC", "§fVenez UHC\"", " ","§b> §fClic gauche pour appliquer","§7Mute 3m"}, SanctionCategory.MESSAGE, Material.TNT, 12, SanctionType.MUTE, 1000*60*3),
    BAD_LANGUAGE("§aMauvais language", "Mauvais language", new String[]{"§fUtilise des mots grossiers sans insulter"," ","§fExemple:","§f\"Bordel de merde ça me fait chier ce jeu\"", " ", "§b> §fClic gauche pour appliquer","§7Mute 15m"}, SanctionCategory.MESSAGE, Material.ROTTEN_FLESH, 13, SanctionType.MUTE, 1000*60*15),
    PROVOCATION("§aProvocation / Vantardise", "Provocation / Vantardise", new String[]{"§fProvoque un autre joueur ou se vante", "§fen le rabaissant"," ","§fExemples:","§f\"ez\"", "§f\"t'es nul comparé à moi\"", " ", "§b> §fClic gauche pour appliquer","§7Mute 10m"}, SanctionCategory.MESSAGE, Material.POTION, 14, SanctionType.MUTE, 1000*60*10),
    INSULT("§aInsulte", "Insulte", new String[]{"§fInsulte envers quelqu'un","§f/objet/entité/serveur"," ","§fExemples:","§f\"C'est de la merde ce jeu\"", "§f\"t'es trop con\"", " ", "§b> §fClic gauche pour appliquer","§7Mute 30m"}, SanctionCategory.MESSAGE, Material.SKULL_ITEM, 15, "MHF_Zombie", SanctionType.MUTE, 1000*60*30),
    FORBIDDEN_LINK("§aLien interdit", "Lien interdit", new String[]{"§fLien vers un site interdit (porno,", "§fparis sportifs, mais sans hack!)", " ", "§b> §fClic gauche pour appliquer","§7Ban 1j"}, SanctionCategory.MESSAGE, Material.BARRIER, 19, (byte) 14, SanctionType.BAN, 1000*60*60*24),
    AD("§aPub", "Pub", new String[]{"§fPublicité pour un autre", "§fserveur / discord ou autre", " ", "§b> §fClic gauche pour appliquer","§7Mute 7j"}, SanctionCategory.MESSAGE, Material.BANNER, 20, SanctionType.MUTE, 1000*60*60*24*7),
    HACK("§aHack / DDOS / Phishing", "Hack / DDOS / Phishing", new String[]{"§fTentative de hack", " ", "§fExemples:", "§f\"Gagnez de l'argent paypal sur http://jevaistebaiser.com\"", "§f\"Je vais te ddos\"", " ", "§b> §fClic gauche pour appliquer","§7Ban 7j"}, SanctionCategory.MESSAGE, Material.FISHING_ROD, 21, SanctionType.BAN, 1000*60*60*24*7),
    INVALID_USERNAME("§aPseudo incorrect", "Pseudo incorrect", new String[]{"§fUsurpation d'identité ou insultant", " ", "§b> §fClic gauche pour appliquer","§7Ban 1000j"}, SanctionCategory.MESSAGE, Material.NAME_TAG, 22, SanctionType.BAN, 1000*60*60*24*1000),
    THREAT("§aMenaces IRL", "MMenaces IRL", new String[]{"§fGrosses menaces IRL, pas juste","§f'toi t'es mort' sur un coup de rage", " ", "§b> §fClic gauche pour appliquer","§7Ban 1h"}, SanctionCategory.MESSAGE, Material.SKULL_ITEM, 23, "Rypex", SanctionType.BAN, 1000*60*60);
    ;

    private String title;
    private String reason;
    private String[] lore;
    private SanctionCategory sanctionCategory;
    private int inventorySlot;
    private byte durability;
    private Material inventoryMaterial;
    private String skullOwner;
    private SanctionType sanctionType;
    private long time;

    Sanction(String title, String reason, String[] lore, SanctionCategory sanctionCategory, Material inventoryMaterial, int inventorySlot, SanctionType sanctionType, long time){
        this.title = title;
        this.reason = reason;
        this.lore = lore;
        this.sanctionCategory = sanctionCategory;
        this.inventoryMaterial = inventoryMaterial;
        this.inventorySlot = inventorySlot;
        this.sanctionType = sanctionType;
        this.time = time;
    }

    Sanction(String title, String reason, String[] lore, SanctionCategory sanctionCategory, Material inventoryMaterial, int inventorySlot, byte durability, SanctionType sanctionType, long time){
        this.title = title;
        this.reason = reason;
        this.lore = lore;
        this.sanctionCategory = sanctionCategory;
        this.inventoryMaterial = inventoryMaterial;
        this.inventorySlot = inventorySlot;
        this.sanctionType = sanctionType;
        this.time = time;
        this.durability = durability;
    }

    Sanction(String title, String reason, String[] lore, SanctionCategory sanctionCategory, Material inventoryMaterial, int inventorySlot, String skullOwner, SanctionType sanctionType, long time){
        this.title = title;
        this.reason = reason;
        this.lore = lore;
        this.sanctionCategory = sanctionCategory;
        this.inventoryMaterial = inventoryMaterial;
        this.inventorySlot = inventorySlot;
        this.skullOwner = skullOwner;
        this.sanctionType = sanctionType;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String[] getLore() {
        return lore;
    }

    public SanctionCategory getSanctionCategory() {
        return sanctionCategory;
    }

    public int getInventorySlot() {
        return inventorySlot;
    }

    public SanctionType getSanctionType() {
        return sanctionType;
    }

    public long getTime() {
        return time;
    }

    public String getSkullOwner() {
        return skullOwner;
    }


    public Material getInventoryMaterial() {
        return inventoryMaterial;
    }

    public static List<Sanction> getMessagesSanctions(){
        List<Sanction> messagesSanction = new ArrayList<>();
        for (Sanction sanction : Sanction.values()){
            if (sanction.sanctionCategory == SanctionCategory.MESSAGE){
                messagesSanction.add(sanction);
            }
        }
        return messagesSanction;
    }

    public static Sanction getSanctionByTitle(String title){
        for (Sanction sanction : Sanction.values()){
            if (sanction.getTitle().equalsIgnoreCase(title)){
                return sanction;
            }
        }
        return null;
    }

    public byte getDurability() {
        return durability;
    }

    public String getReason() {
        return reason;
    }
}
