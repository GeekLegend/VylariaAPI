package fr.vylaria.api.inventory.inventories;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.inventory.AbstractInventory;
import fr.vylaria.api.inventory.inventories.manager.InventoryManager;
import fr.vylaria.api.sanctions.Sanction;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class SanctionMessagesInventory extends AbstractInventory implements Listener {

    private String player;

    public SanctionMessagesInventory() {
        super(6*9, "§4SS §7>> §6Messages");
    }

    public SanctionMessagesInventory(String player) {
        super(6*9, "§4SS §7>> §6Messages");
        this.player = player;
    }

    @Override
    public Inventory create(Player p) {

        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account account = redisAccount.get(p.getUniqueId());

        String tempName = name.replace("<player>", p.getName());

        inventory = Bukkit.createInventory(null, size, tempName);

        inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(4, new ItemBuilder(Material.SKULL_ITEM).setDurability((byte) 3)
                .setSkullOwner(this.player)
                .setName("§6" + name)
                .setLore("§f§nRank:§f " + account.getRank().getName(), "§f§nLangue:§f " + account.getLang(), "§f§nCoins:§f " + account.getCoins(), "§f§nEurs:§f " + account.getEur(), "§f§n1ère connexion:§f " + account.getFirstConnection().toString(), "§f§nDernière connexion:§f " + account.getLastConnection().toString())
                .toItemStack());
        inventory.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());

        for (Sanction sanction : Sanction.getMessagesSanctions()){

            ItemBuilder ib = new ItemBuilder(sanction.getInventoryMaterial()).setName(sanction.getTitle()).setLore(sanction.getLore());
            if (sanction.getSkullOwner() != null){
                ib.setDurability((byte) 3).setSkullOwner(sanction.getSkullOwner());
            }else if(sanction.getDurability() != 0){
                ib.setDurability(sanction.getDurability());
            }
            inventory.setItem(sanction.getInventorySlot(), ib.toItemStack());

        }

        inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(47, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(48, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(49, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(50, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(51, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(size - 1, new ItemBuilder(Material.BARRIER).setName("§cRetour").toItemStack());

        return inventory;

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ClickType clickType = event.getClick();
        ItemStack item = event.getCurrentItem();

        if (inventory != null && inventory.getName().equalsIgnoreCase("§4SS §7>> §6Messages"))
        {
            event.setCancelled(true);

            if (item != null)
            {
                switch (item.getType())
                {
                    case BARRIER:
                        if (clickType.isLeftClick()) player.openInventory(VylariaAPI.getInstance().getInventoryManager().getSanctionInventory().create(player));
                        break;
                    case STAINED_GLASS_PANE:
                    case AIR:
                        break;
                    default:
                        if (clickType.isLeftClick()) player.openInventory(VylariaAPI.getInstance().getInventoryManager().getSanctionApplyInventory().create(player, Sanction.getSanctionByTitle(item.getItemMeta().getDisplayName())));
                        break;
                }
            }
        }
    }

    public void refresh()
    {
        for (Player players : Bukkit.getOnlinePlayers())
        {
            InventoryView inventoryView = players.getOpenInventory();
            if (inventoryView.getTitle().equalsIgnoreCase(name))
            {
                Inventory contents = inventoryView.getTopInventory();

                contents.clear();
                inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(10, new ItemBuilder(Material.SEEDS)
                        .setName("§6Message inutile")
                        .setLore("§fMessage qui n'apporte rien à personne","§fet qui encombre le chat sans raison"," ","§fExemple:","§fqfsegzefqfqf", " ","§b> §fClic gauche pour appliquer","§7Mute 1m")
                        .toItemStack());
                inventory.setItem(11, new ItemBuilder(Material.IRON_HOE)
                        .setName("§6Fausse information")
                        .setLore("§fMessage mensonger, information fausse"," ","§fExemple:","§f\"J'étais modérateur avant\"", " ","§b> §fClic gauche pour appliquer","§7Mute 3m")
                        .toItemStack());
                inventory.setItem(12, new ItemBuilder(Material.TNT)
                        .setName("§6Flood")
                        .setLore("§fEnvoi du même message plusieurs fois","§fen un court interval de temps"," ","§fExemple:","§f\"Venez UHC", "§fVenez UHC", "§fVenez UHC", "§fVenez UHC\"", " ","§b> §fClic gauche pour appliquer","§7Mute 3m")
                        .toItemStack());
                inventory.setItem(13, new ItemBuilder(Material.ROTTEN_FLESH)
                        .setName("§6Mauvais language")
                        .setLore("§fUtilise des mots grossiers sans insulter"," ","§fExemple:","§f\"Bordel de merde ça me fait chier ce jeu\"", " ", "§b> §fClic gauche pour appliquer","§7Mute 15m")
                        .toItemStack());
                inventory.setItem(14, new ItemBuilder(Material.POTION)
                        .setName("§6Provocation / Vantardise")
                        .setLore("§fProvoque un autre joueur ou se vante", "§fen le rabaissant"," ","§fExemples:","§f\"ez\"", "§f\"t'es nul comparé à moi\"", " ", "§b> §fClic gauche pour appliquer","§7Mute 10m")
                        .toItemStack());
                inventory.setItem(15, new ItemBuilder(Material.SKULL_ITEM)
                        .setSkullOwner("MHF_Zombie")
                        .setName("§6Insulte")
                        .setLore("§fInsulte envers quelqu'un","§f/objet/entité/serveur"," ","§fExemples:","§f\"C'est de la merde ce jeu\"", "§f\"t'es trop con\"", " ", "§b> §fClic gauche pour appliquer","§7Mute 30m")
                        .toItemStack());
                inventory.setItem(19, new ItemBuilder(Material.BARRIER)
                        .setName("§6Lien interdit")
                        .setLore("§fLien vers un site interdit (porno,", "§fparis sportifs, mais sans hack!)", " ", "§b> §fClic gauche pour appliquer","§7Ban 7j")
                        .toItemStack());
                inventory.setItem(20, new ItemBuilder(Material.BANNER)
                        .setName("§6Pub")
                        .setLore("§fPublicité pour un autre", "§fserveur / discord ou autre", " ", "§b> §fClic gauche pour appliquer","§7Ban 7j")
                        .toItemStack());
                inventory.setItem(21, new ItemBuilder(Material.FISHING_ROD)
                        .setName("§6Hack / DDOS / Phishing")
                        .setLore("§fTentative de hack", " ", "§fExemples:", "§f\"Gagnez de l'argent paypal sur http://jevaistebaiser.com\"", "§f\"Je vais te ddos\"", " ", "§b> §fClic gauche pour appliquer","§7Ban 7j")
                        .toItemStack());
                inventory.setItem(22, new ItemBuilder(Material.NAME_TAG)
                        .setName("§6Pseudo incorrect")
                        .setLore("§fUsurpation d'identité ou insultant", " ", "§b> §fClic gauche pour appliquer","§7Ban 1000j")
                        .toItemStack());
                inventory.setItem(23, new ItemBuilder(Material.SKULL_ITEM)
                        .setSkullOwner("Rypex")
                        .setName("§6Menaces IRL")
                        .setLore("§fGrosses menaces IRL, pas juste","§f'toi t'es mort' sur un coup de rage", " ", "§b> §fClic gauche pour appliquer","§7Ban 1h")
                        .toItemStack());
                inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(47, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(48, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(49, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(50, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(51, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                inventory.setItem(size - 1, new ItemBuilder(Material.BARRIER).setName("§cRetour").toItemStack());
            }
        }
    }

}
