package me.mrh1tech.policebaton.listeners;

import me.mrh1tech.policebaton.PoliceBaton;
import me.mrh1tech.policebaton.configs.ItemConfig;
import me.mrh1tech.policebaton.managers.FreezeManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class AttackListener implements Listener {

    private final PoliceBaton plugin;
    private final ItemConfig itemConfig;
    private final FreezeManager freezeManager;

    private final long freezeDuration;

    public AttackListener(PoliceBaton plugin) {
        this.plugin = plugin;
        this.itemConfig = ItemConfig.getInstance();
        this.freezeManager = FreezeManager.getInstance();

        this.freezeDuration = plugin.getConfig().getInt("duration.freeze");
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player)) return;

        Player damager = (Player) event.getDamager();
        Player player = (Player) event.getEntity();

        if (!damager.getInventory().getItemInMainHand().getType().equals(itemConfig.getItem().getType())) return;

        ItemMeta itemMeta = damager.getInventory().getItemInMainHand().getItemMeta();
        ItemMeta configMeta = itemConfig.getItem().getItemMeta();

        if (!itemMeta.getDisplayName().equals(configMeta.getDisplayName()) ||
                !itemMeta.getLore().equals(configMeta.getLore())) return;

        freezeManager.freezePlayer(player, freezeDuration);

        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                plugin.getConfig().getString("messages.freeze-message")
                        )
                        .replaceAll("%player%", damager.getDisplayName())
                        .replaceAll("%duration%", String.valueOf(freezeDuration))
        );

        damager.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                plugin.getConfig().getString("messages.you-froze-player")
                        )
                        .replaceAll("%player%", player.getDisplayName())
        );
    }

}