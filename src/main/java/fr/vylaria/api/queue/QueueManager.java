package fr.vylaria.api.queue;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.inventory.InventoryUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class QueueManager
{

    public static final String QUEUE_PREFIX = "§7[§eFile d'attente§7] ";

    private Map<Player, Queue> queues;
    InventoryUtils inventoryUtils;

    public QueueManager()
    {
        this.queues = new HashMap<Player, Queue>();
        this.inventoryUtils = VylariaAPI.getInstance().getIu();
    }

    public void add(Player player, Queue queue)
    {
        queue.add(player);
        queues.put(player, queue);

        inventoryUtils.save(player);

        player.getInventory().setItem(0, new ItemBuilder(Material.BARRIER).setName("§cQuitter").toItemStack());
        player.sendMessage(QUEUE_PREFIX + "§7Vous êtes placer §e" + getQueue(player).getPosition() + "/" + queues.size() + " §7dans la file d'attente.");
    }

    public void remove(Player player, Queue queue)
    {
        queue.remove(player);
        queues.remove(player);

        inventoryUtils.restore(player);

        player.sendMessage(QUEUE_PREFIX + "§7Vous avez quitter la file d'attente.");
    }

    public void update(Player player)
    {
        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account account = redisAccount.get(player.getUniqueId());
        Rank rank = account.getRank();
        Queue queue = getQueue(player);

        if (queue != null)
        {
            if (rank.getPower() <= Rank.VIP.getPower())
            {
                player.sendMessage(QUEUE_PREFIX + "");

                remove(player, queue);
            }
        }
    }

    public Queue getQueue(Player player)
    {
        return queues.get(player);
    }

    public boolean contains(Player player)
    {
        return queues.containsKey(player);
    }

}
