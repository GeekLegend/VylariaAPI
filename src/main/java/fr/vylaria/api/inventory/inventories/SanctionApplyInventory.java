package fr.vylaria.api.inventory.inventories;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.account.link.Link;
import fr.vylaria.api.account.link.RedisLink;
import fr.vylaria.api.account.mute.Mute;
import fr.vylaria.api.account.mute.RedisMute;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.inventory.AbstractInventory;
import fr.vylaria.api.sanctions.Sanction;
import fr.vylaria.api.sanctions.SanctionType;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.sql.Timestamp;

public class SanctionApplyInventory extends AbstractInventory implements Listener {

    private String player;

    public SanctionApplyInventory() {
        super(6*9, "§4SS §7>> §a<sanction>");
    }

    public SanctionApplyInventory(String player) {
        super(6*9, "§4SS §7>> §a<sanction>");
        this.player = player;
    }

    @Override
    public Inventory create(Player player) {
        return null;
    }

    public Inventory create(Player p, Sanction sanction) {

        this.player = p.getDisplayName();

        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account account = redisAccount.get(p.getUniqueId());

        String tempName = name.replace("<player>", p.getName()).replace("<sanction>", sanction.getTitle());

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



        inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());

        ItemBuilder sanctionBuilder = new ItemBuilder(sanction.getInventoryMaterial()).setName(sanction.getTitle()).setLore(sanction.getLore());
        if (sanction.getSkullOwner() != null){
            sanctionBuilder.setDurability((byte) 3).setSkullOwner(sanction.getSkullOwner());
        }else if(sanction.getDurability() != 0){
            sanctionBuilder.setDurability(sanction.getDurability());
        }
        inventory.setItem(22, sanctionBuilder.toItemStack());

        inventory.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());

        inventory.setItem(30, new ItemBuilder(Material.INK_SACK).setDurability((byte) 10).setName("§aAppliquer la sanction").toItemStack());
        inventory.setItem(32, new ItemBuilder(Material.INK_SACK).setDurability((byte) 1).setName("§cAnnuler").toItemStack());

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
        Player p = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ClickType clickType = event.getClick();
        ItemStack item = event.getCurrentItem();

        if (inventory != null && inventory.getName().startsWith("§4SS §7>> §a"))
        {
            event.setCancelled(true);

            if (item != null)
            {
                switch (item.getType())
                {
                    case BARRIER:
                        if (clickType.isLeftClick()) p.openInventory(VylariaAPI.getInstance().getInventoryManager().getSanctionMessagesInventory().create(p));
                        break;
                    case INK_SACK:
                        if (item.getDurability() == 1 && clickType.isLeftClick()){
                            p.openInventory(VylariaAPI.getInstance().getInventoryManager().getSanctionMessagesInventory().create(p));
                            break;
                        }else if(item.getDurability() == 10 && clickType.isLeftClick()){
                            Sanction sanction = Sanction.getSanctionByTitle(item.getItemMeta().getDisplayName());
                            if(sanction != null && this.player != null){
                                if (sanction.getSanctionType() == SanctionType.MUTE){
                                    RedisLink redisLink = VylariaAPI.getInstance().getRedisLink();
                                    Link link = redisLink.get(this.player);



                                    RedisMute redisMute = VylariaAPI.getInstance().getRedisMute();
                                    redisMute.create(new Mute(this.player.getUniqueId(), sanction.getReason(), new Timestamp((System.currentTimeMillis() + sanction.getTime())), p.getDisplayName()));
                                }
                            }
                        }
                    default:
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
                contents.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(4, new ItemBuilder(Material.SKULL_ITEM).setDurability((byte) 3).setSkullOwner(this.player.getDisplayName()).setName("§6" + name).toItemStack());
                contents.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(47, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(48, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(49, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(50, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(51, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
                contents.setItem(size - 1, new ItemBuilder(Material.BARRIER).setName("§cFermer le menu").toItemStack());
            }
        }
    }

}
