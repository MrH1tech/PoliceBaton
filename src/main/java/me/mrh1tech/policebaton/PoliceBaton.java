package me.mrh1tech.policebaton;

import me.mrh1tech.policebaton.commands.PoliceBatonCommand;
import me.mrh1tech.policebaton.listeners.AttackListener;
import me.mrh1tech.policebaton.listeners.MovementListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class PoliceBaton extends JavaPlugin {

    private static PoliceBaton instance;

    @Override
    public void onEnable() {
        instance = this;

        initConfig();
        initCommands();
        initListeners();
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    private void initConfig() {
        File config = new File(getDataFolder() + "/config.yml");

        if (config.exists()) return;

        try {
            getLogger().info("Creating config file...");

            getConfig().options().copyDefaults(true);
            saveDefaultConfig();

            getLogger().info("Creating config file is DONE!");
        } catch (Exception ex) {
            ex.printStackTrace();
            getLogger().info("Creating config file is FAILED!");
        }
    }

    private void initCommands() {
        getCommand("policebaton").setExecutor(new PoliceBatonCommand(this));
        getCommand("policebaton").setTabCompleter(new PoliceBatonCommand(this));
    }

    private void initListeners() {
        Bukkit.getPluginManager().registerEvents(new AttackListener(this), this);
        Bukkit.getPluginManager().registerEvents(new MovementListener(this), this);
    }

    public static PoliceBaton getInstance() {
        return instance;
    }

}