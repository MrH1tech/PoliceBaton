package me.mrh1tech.policebaton.configs;

import me.mrh1tech.policebaton.PoliceBaton;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemConfig {

    private static ItemConfig instance;

    private final ItemStack item;
    private final String name;

    private ItemConfig() {
        PoliceBaton plugin = PoliceBaton.getInstance();

        this.name = ChatColor.translateAlternateColorCodes('&',
                plugin.getConfig().getString("item.name")
        );
        List<String> lore = new ArrayList<>();
        for (String line : plugin.getConfig().getStringList("item.lore"))
            lore.add(ChatColor.translateAlternateColorCodes('&', line));

        Material material = Material.valueOf(plugin.getConfig().getString("item.material").toUpperCase());

        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(this.name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public static ItemConfig getInstance() {
        if (instance == null) instance = new ItemConfig();
        return instance;
    }

}