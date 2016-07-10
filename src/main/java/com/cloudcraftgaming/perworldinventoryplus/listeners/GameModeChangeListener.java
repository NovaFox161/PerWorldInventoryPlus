package com.cloudcraftgaming.perworldinventoryplus.listeners;

import com.cloudcraftgaming.perworldinventoryplus.Main;
import com.cloudcraftgaming.perworldinventoryplus.inventory.InventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

/**
 * Created by Nova Fox on 6/9/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: PerWorldInventoryPlus.
 */
public class GameModeChangeListener implements Listener {
    Main plugin;

    public GameModeChangeListener(Main instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onGameModeChangeInventory(PlayerGameModeChangeEvent event) {
        if (!event.isCancelled()) {
            if (plugin.getConfig().getString("Global.GameModeInventories").equalsIgnoreCase("True")) {
                Player player = event.getPlayer();
                String worldName = player.getWorld().getName();
                InventoryManager.getManager().saveInventory(player, worldName, player.getGameMode());

                InventoryManager.getManager().loadInventory(player, worldName, event.getNewGameMode());
            }
        }
    }
}
