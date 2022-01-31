package me.mrh1tech.policebaton;

import org.bukkit.entity.Player;

import java.util.Objects;

public class FrozenPlayer {

    private Player player;
    private long startTime;
    private long durability;

    public FrozenPlayer(Player player, long startTime, long durability) {
        this.player = player;
        this.startTime = startTime;
        this.durability = durability;
    }

    public FrozenPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDurability() {
        return durability;
    }

    public void setDurability(long durability) {
        this.durability = durability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrozenPlayer that = (FrozenPlayer) o;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }

}
