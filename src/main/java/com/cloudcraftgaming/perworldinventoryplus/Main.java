package com.cloudcraftgaming.perworldinventoryplus;

import com.cloudcraftgaming.perworldinventoryplus.listeners.GameModeChangeListener;
import com.cloudcraftgaming.perworldinventoryplus.listeners.JoinListener;
import com.cloudcraftgaming.perworldinventoryplus.listeners.QuitListener;
import com.cloudcraftgaming.perworldinventoryplus.utils.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Nova Fox on 6/9/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: PerWorldInventoryPlus.
 */
public class Main extends JavaPlugin {
    public static Main plugin;

    public void onDisable() {}

    public void onEnable() {
        plugin = this;

        //Register listeners
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new GameModeChangeListener(this), this);

        //Create and do all file things here
        FileManager.createConfig();

        FileManager.checkFileVersions();

    }
}