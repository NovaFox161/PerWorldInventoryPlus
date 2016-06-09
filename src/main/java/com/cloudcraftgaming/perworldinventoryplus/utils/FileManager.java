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

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();
        }
    }
}
