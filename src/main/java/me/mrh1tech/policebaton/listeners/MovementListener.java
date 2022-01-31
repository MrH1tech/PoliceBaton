package me.mrh1tech.policebaton.listeners;

import me.mrh1tech.policebaton.PoliceBaton;
import me.mrh1tech.policebaton.managers.FreezeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {

    private final PoliceBaton plugin;
    private final FreezeManager freezeManager;

    public MovementListener(PoliceBaton plugin) {
        this.plugin = plugin;
        this.freezeManager = FreezeManager.getInstance();
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!freezeManager.isPlayerFrozen(event.getPlayer())) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!freezeManager.isPlayerFrozen(event.getPlayer())) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!freezeManager.isPlayerFrozen(event.getPlayer())) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!freezeManager.isPlayerFrozen(event.getPlayer())) return;

        event.setCancelled(true);
    }

}