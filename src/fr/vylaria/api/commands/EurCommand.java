package fr.vylaria.api.commands;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.AccountNoFoundException;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.money.MoneyManager;
import fr.vylaria.api.utils.Constants;
import fr.vylaria.api.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EurCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account account = redisAccount.get(player.getUniqueId());
        Rank rank = account.getRank();
        String prefix = MoneyManager.EUR_PREFIX;

        if (rank.getPower() >= Rank.ADMIN.getPower())
        {
            if (args.length == 0)
            {
                player.sendMessage("§e===== §e§lEur - Aide §e=====");
                player.sendMessage("§e/eur add <player> <amount> §7- Donner des euros à un joueur.");
                player.sendMessage("§e/eur remove <player> <amount> §7- Retirer des euros à un joueur.");

                return false;
            } else if (args[0].equalsIgnoreCase("add"))
            {
                if (args.length < 3)
                {
                    player.sendMessage(prefix + "§c/coins add <player> <amount>");

                    return false;
                } else
                {
                    Player target = VylariaAPI.getInstance().getServer().getPlayer(args[1]);

                    if (!VylariaAPI.getInstance().getServer().getOnlinePlayers().contains(target))
                    {
                        player.sendMessage(prefix + "§cLe joueur " + StringUtils.capitalize(args[1]) + " n'est pas en ligne.");

                        return false;
                    } else
                    {
                        if (Utils.isInt(args[2]))
                        {
                            float amount = Float.valueOf(args[2]);

                            if (amount <= 0) return false;

                            RedisAccount targetRedisAccount = VylariaAPI.getInstance().getRedisAccount();
                            Account targetAccount = targetRedisAccount.get(target.getUniqueId());
                            float targetEur = targetAccount.getEur();
                            float newAmount = targetEur + amount;

                            targetAccount.setEur(newAmount);

                            try
                            {
                                targetRedisAccount.update(targetAccount);
                            } catch (AccountNoFoundException e)
                            {
                                e.printStackTrace();
                            }

                            player.sendMessage(prefix + "§7Vous venez de donner §e" + args[2] + " §7euros à " + StringUtils.capitalize(args[1]) + ".");
                            target.sendMessage(prefix + "§e" + args[2] + " §7euros vous on était ajouter.");
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("remove"))
            {
                if (args.length < 3)
                {
                    player.sendMessage(prefix + "§c/coins remove <player> <amount>");

                    return false;
                } else
                {
                    Player target = VylariaAPI.getInstance().getServer().getPlayer(args[1]);

                    if (!VylariaAPI.getInstance().getServer().getOnlinePlayers().contains(target))
                    {
                        player.sendMessage(prefix + "§cLe joueur " + StringUtils.capitalize(args[1]) + " n'est pas en ligne.");

                        return false;
                    } else
                    {
                        if (Utils.isInt(args[2]))
                        {
                            float amount = Float.valueOf(args[2]);

                            if (amount <= 0) return false;

                            RedisAccount targetRedisAccount = VylariaAPI.getInstance().getRedisAccount();
                            Account targetAccount = targetRedisAccount.get(target.getUniqueId());
                            float targetEur = targetAccount.getEur();
                            float newAmount = targetEur - amount;

                            targetAccount.setEur(newAmount);

                            try
                            {
                                targetRedisAccount.update(targetAccount);
                            } catch (AccountNoFoundException e)
                            {
                                e.printStackTrace();
                            }

                            player.sendMessage(prefix + "§7Vous venez de retirer §e" + args[2] + " §7euros à " + StringUtils.capitalize(args[1]) + ".");
                            target.sendMessage(prefix + "§e" + args[2] + " §7euros vous on était retirer.");
                        }
                    }
                }
            }
        } else
        {
            player.sendMessage(Constants.NO_PERMISSION);
        }
        return false;
    }

}
