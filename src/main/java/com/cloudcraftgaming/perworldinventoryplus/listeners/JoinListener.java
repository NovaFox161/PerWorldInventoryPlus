package com.cloudcraftgaming.perworldinventoryplus.listeners;

import com.cloudcraftgaming.perworldinventoryplus.Main;
import com.cloudcraftgaming.perworldinventoryplus.inventory.InventoryManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Nova Fox on 6/9/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: PerWorldInventoryPlus.
 */
public class JoinListener implements Listener {
    Main plugin;
    public JoinListener(Main instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoinLoadInventory(PlayerJoinEvent event) {
        if (plugin.getConfig().getString("Player.Inventory").equalsIgnoreCase("True")) {
            Player player = event.getPlayer();
            String worldName = player.getWorld().getName();
            GameMode gm = player.getGameMode();
            InventoryManager.getManager().loadInventory(player, worldName, gm);
        }
    }
}
