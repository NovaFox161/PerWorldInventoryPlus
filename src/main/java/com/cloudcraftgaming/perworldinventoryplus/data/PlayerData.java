package com.cloudcraftgaming.perworldinventoryplus.data;

import com.cloudcraftgaming.perworldinventoryplus.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

/**
 * Created by Nova Fox on 6/9/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: PerWorldInventoryPlus.
 */
public class PlayerData {
    public static void createPlayerData(Player player) {
        UUID id = player.getUniqueId();

        File file = new File(Main.plugin.getDataFolder() + "/Data/Players/" + id + "/data.yml");
        if (!file.exists()) {
            Main.plugin.getLogger().info("Generating player data for player: " + player.getName());

            YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
            data.addDefault("DO NOT DELETE", "PerWorldInventoryPlus is developed and managed by Shades161");

            data.addDefault("Name", player.getName());


            data.options().copyDefaults(true);
            savePlayerData(data, file);

            data.options().copyDefaults(true);
            savePlayerData(data, file);
        }
    }
    public static void savePlayerData(YamlConfiguration dataYml, File dataFile) {
        try {
           dataYml.save(dataFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getPlayerDataFile(Player player) {
        return new File(Main.plugin.getDataFolder() + "/Data/Players/" + player.getUniqueId() + "/data.yml");
    }
    public static YamlConfiguration getPlayerDataYml(Player player) {
        return YamlConfiguration.loadConfiguration(getPlayerDataFile(player));
    }
}
