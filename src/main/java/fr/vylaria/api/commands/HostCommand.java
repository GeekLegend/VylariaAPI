package fr.vylaria.api.commands;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.host.HostManager;
import fr.vylaria.api.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HostCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account account = redisAccount.get(player.getUniqueId());
        Rank rank = account.getRank();
        String prefix = "§7[§eHost§7] ";
        String suffix = HostManager.PREFIX;

        if (rank.getPower() >= Rank.ADMIN.getPower())
        {
            if (args.length == 0)
            {
                player.sendMessage("§e===== §e§lHost §e- §e§lAide §e=====");
                player.sendMessage("§e/host add <player> §7- Ajouter un joueur en tant que Host.");
                player.sendMessage("§e/host remove <player> §7- Supprimer un joueur du grade Host.");

                return false;
            } else if (args[0].equalsIgnoreCase("add"))
            {
                if (args.length < 2)
                {
                    player.sendMessage("§c/host add <player>");

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
                        Account targetAccount = redisAccount.get(target.getUniqueId());

                        if (targetAccount.getSuffix().equalsIgnoreCase(suffix))
                        {
                            player.sendMessage(prefix + "§cLe joueur " + StringUtils.capitalize(args[1]) + " est déjà Host.");

                            return false;
                        } else if (targetAccount.getSuffix().isEmpty())
                        {
                            targetAccount.setSuffix(suffix);
                            redisAccount.update(targetAccount);

                            player.sendMessage(prefix + "§7Le joueur §e" + StringUtils.capitalize(args[1]) + " §7est désormais §eHost§7.");
                            target.sendMessage(prefix + "§7Vous êtes désormais §eHost§7.");
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("remove"))
            {
                if (args.length < 2)
                {
                    player.sendMessage("§c/host remove <player>");

                    return false;
                } else
                {
                    Player target = VylariaAPI.getInstance().getServer().getPlayer(args[1]);
                    Account targetAccount = redisAccount.get(target.getUniqueId());

                    if (!VylariaAPI.getInstance().getServer().getOnlinePlayers().contains(target))
                    {
                        player.sendMessage(prefix + "§cLe joueur " + StringUtils.capitalize(args[1]) + " n'est pas en ligne.");

                        return false;
                    } else
                    {
                        if (!targetAccount.getSuffix().equalsIgnoreCase(suffix))
                        {
                            player.sendMessage(prefix + "§cLe joueur " + StringUtils.capitalize(args[1]) + " n'est pas Host.");

                            return false;
                        } else
                        {
                            if (!targetAccount.getSuffix().isEmpty())
                            {
                                targetAccount.setSuffix("");
                                redisAccount.update(targetAccount);

                                player.sendMessage(prefix + "§7Le joueur §e" + StringUtils.capitalize(args[1]) + " §7n'est plus §eHost§7.");
                                target.sendMessage(prefix + "§7Vous n'êtes plus §eHost§7.");
                            }
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
