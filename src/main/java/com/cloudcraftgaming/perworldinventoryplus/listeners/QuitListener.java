package com.cloudcraftgaming.perworldinventoryplus.listeners;

import com.cloudcraftgaming.perworldinventoryplus.Main;
import com.cloudcraftgaming.perworldinventoryplus.inventory.InventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Nova Fox on 6/9/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: PerWorldInventoryPlus.
 */
public class QuitListener implements Listener {
    Main plugin;
    public QuitListener(Main instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuitSaveInventory(PlayerQuitEvent event) {
        if (plugin.getConfig().getString("Player.Inventory").equalsIgnoreCase("True")) {
            Player player = event.getPlayer();
            InventoryManager.getManager().saveInventory(player, player.getWorld().getName(), player.getGameMode());
        }
    }
}