package fr.vylaria.api.commands;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.rank.RankManager;
import fr.vylaria.api.utils.Constants;
import fr.vylaria.api.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        RedisAccount redisAccount = VylariaAPI.getInstance().getRedisAccount();
        Account playerAccount = redisAccount.get(player.getUniqueId());
        Rank playerRank = playerAccount.getRank();
        String prefix = RankManager.PREFIX;

        if (playerRank.getPower() >= Rank.RESP_MOD.getPower())
        {
            if (args.length == 0)
            {
                player.sendMessage(prefix + "§c/rank set <player> <power>");

                return false;
            } else if (args.length >= 2)
            {
                if (args.length < 3)
                {
                    player.sendMessage(prefix + "§c/rank set <player> <power>");

                    return false;
                } else
                {
                    Player target = Bukkit.getPlayer(args[1]);

                    if (target != player)
                    {
                        if (!Bukkit.getOnlinePlayers().contains(target))
                        {
                            player.sendMessage(prefix + "§cLe joueur " + args[1] + " n'est pas en ligne.");

                            return false;
                        } else
                        {
                            if (Utils.isInt(args[2]))
                            {
                                Account targetAccount = redisAccount.get(target.getUniqueId());
                                Rank targetRank = targetAccount.getRank();

                                if (targetRank.getPower() <= playerRank.getPower())
                                {
                                    Rank rank = Rank.getByPower(Integer.parseInt(args[2]));

                                    if (rank != null)
                                    {
                                        targetAccount.setRank(rank);
                                        redisAccount.update(targetAccount);

                                        player.sendMessage(prefix + "§aVous avez mis " + target.getName() + " " + rank.getColor() + rank.getPrefix().replace("[", "").replace("]", "") + "§a.");
                                        target.sendMessage(prefix + "§aVous êtes désormais " + rank.getColor() + rank.getPrefix().replace("[", "").replace("]", "") + "§a.");
                                    }
                                } else
                                {
                                    player.sendMessage(prefix + "§cVoyons, tu ne peux pas faire cela sur " + args[1] + "...");
                                }
                            }
                        }
                    } else
                    {
                        player.sendMessage(prefix + "§cTu ne peux pas faire cela sur toi même...");
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
