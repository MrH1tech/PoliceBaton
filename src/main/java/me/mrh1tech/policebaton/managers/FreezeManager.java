package me.mrh1tech.policebaton.managers;

import me.mrh1tech.policebaton.FrozenPlayer;
import me.mrh1tech.policebaton.PoliceBaton;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FreezeManager {

    private static FreezeManager instance;

    private final List<FrozenPlayer> frozenPlayers;
    private final String unfreezeMessage;

    private FreezeManager() {
        this.frozenPlayers = new ArrayList<>();
        this.unfreezeMessage = ChatColor.translateAlternateColorCodes('&',
                PoliceBaton.getInstance().getConfig().getString("messages.unfreeze-message")
        );
    }

    public boolean isPlayerFrozen(Player player) {
        return frozenPlayers.contains(new FrozenPlayer(player));
    }

    public void freezePlayer(Player player, long durability) {
        long currentTime = new Date().getTime();
        FrozenPlayer frozenPlayer = new FrozenPlayer(player, currentTime, durability);

        frozenPlayers.add(frozenPlayer);

        Bukkit.getScheduler().runTaskLater(PoliceBaton.getInstance(), () -> {
            unfreezePlayer(player);
            player.sendMessage(unfreezeMessage);
        }, durability*20);
    }

    public void unfreezePlayer(Player player) {
        FrozenPlayer frozenPlayer = new FrozenPlayer(player);

        frozenPlayers.remove(frozenPlayer);
    }

    public List<FrozenPlayer> getFrozenPlayers() {
        return frozenPlayers;
    }

    public static FreezeManager getInstance() {
        if (instance == null) instance = new FreezeManager();
        return instance;
    }

}