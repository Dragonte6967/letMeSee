package top.dragonte.letMeSee;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class cmdExe implements TabCompleter, CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("Usage: /letmesee <player>");
            return true;
        }

        Player seen = Bukkit.getPlayer(args[0]);
        if (seen == null) {
            sender.sendMessage("Player not found or not online.");
            return true;
        }

        StringBuilder sb = new StringBuilder();
        ItemStack[] items = seen.getInventory().getContents();
        for (ItemStack item : items) {
            if (item != null) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null && meta.hasDisplayName()) {
                    sb.append(ChatColor.GREEN + meta.getDisplayName().toLowerCase()).append(ChatColor.DARK_GRAY + " x ");
                } else {
                    sb.append(ChatColor.GREEN + item.getType().name().toLowerCase()).append(ChatColor.DARK_GRAY + " x ");
                }
                sb.append(ChatColor.YELLOW);
                sb.append(item.getAmount()).append("\n");
            }
        }

        sender.sendMessage(sb.toString());
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        switch (args.length) {
            case 1 -> {
                return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
            }
            default -> {
                return null;
            }
        }
    }
}
