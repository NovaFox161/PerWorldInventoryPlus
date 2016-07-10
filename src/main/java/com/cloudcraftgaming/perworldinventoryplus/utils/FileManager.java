package com.cloudcraftgaming.perworldinventoryplus.utils;

import com.cloudcraftgaming.perworldinventoryplus.Main;

import java.io.File;

/**
 * Created by Nova Fox on 6/9/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: PerWorldInventoryPlus.
 */
public class FileManager {
    protected static Double conVersion = 1.0;

    public static void createConfig() {
        File file = new File(Main.plugin.getDataFolder() + "/config.yml");

        if (!file.exists()) {
            Main.plugin.getLogger().info("Generating config.yml");

            Main.plugin.getConfig().addDefault("DO NOT DELETE", "PerWorldInventoryPlus is developed and managed by Shades161");
            Main.plugin.getConfig().addDefault("Config Version", conVersion);
            Main.plugin.getConfig().addDefault("Check for Updates", true);

            Main.plugin.getConfig().addDefault("Global.GameModeInventories", true);

            Main.plugin.getConfig().addDefault("Player.Enderchest", true);
            Main.plugin.getConfig().addDefault("Player.Inventory", true);
            Main.plugin.getConfig().addDefault("Player.Stats.Health", true);
            Main.plugin.getConfig().addDefault("Player.Stats.Food", true);
            Main.plugin.getConfig().addDefault("Player.Stats.Exhaustion", true);
            Main.plugin.getConfig().addDefault("Player.Stats.Exp", true);
            Main.plugin.getConfig().addDefault("Player.Stats.Level", true);
            Main.plugin.getConfig().addDefault("Player.Stats.potions", true);
            Main.plugin.getConfig().addDefault("Player.Stats.FireTicks", true);

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();
        }
    }

    public static void checkFileVersions() {
        if (Main.plugin.getConfig().getDouble("Config Version") != conVersion) {
            Main.plugin.getLogger().severe("config.yml outdated! Please copy settings and delete the file!");
            Main.plugin.getLogger().severe("Disabling plugin to prevent further errors!");
            Main.plugin.getServer().getPluginManager().disablePlugin(Main.plugin);
        }
    }
}
