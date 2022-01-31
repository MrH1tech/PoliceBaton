package me.mrh1tech.policebaton.commands;

import me.mrh1tech.policebaton.PoliceBaton;
import me.mrh1tech.policebaton.configs.ItemConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PoliceBatonCommand implements CommandExecutor, TabCompleter {

    private final PoliceBaton plugin;
    private final ItemConfig itemConfig;

    public PoliceBatonCommand(PoliceBaton plugin) {
        this.plugin = plugin;
        this.itemConfig = ItemConfig.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfig().getString("messages.errors.only-for-players"))
            );
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfig().getString("messages.errors.few-arguments"))
            );

            return false;
        }

        String firstSubcommand = args[0];
        if (firstSubcommand.equalsIgnoreCase("getitem")) {
            ItemStack item = itemConfig.getItem();
            player.getInventory().addItem(item);

            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfig().getString("messages.item-was-given"))
            );

            return true;
        }

        if (firstSubcommand.equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfig().getString("messages.config-reloaded"))
            );

            return true;
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("messages.errors.unknown-subcommand"))
        );

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("reload");
            arguments.add("getitem");
            return arguments;
        }

        return new ArrayList<>();
    }

}