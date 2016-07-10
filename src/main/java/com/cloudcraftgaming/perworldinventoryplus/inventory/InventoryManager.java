package com.cloudcraftgaming.perworldinventoryplus.inventory;

import com.cloudcraftgaming.perworldinventoryplus.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by Nova Fox on 6/9/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: PerWorldInventoryPlus.
 *
 * This class manages player's inventories. Specifically, getting, loading, saving.
 */
public class InventoryManager {
    private static InventoryManager instance;

    private InventoryManager() {} //Stop initialization

    public static InventoryManager getManager() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void saveInventory(Player player, String worldName, GameMode gm) {
        if (Main.plugin.getConfig().getString("Player.Inventory").equalsIgnoreCase("True")) {
            if (gm.equals(GameMode.SURVIVAL)) {
                YamlConfiguration inv = getInventoryYml(player, worldName, gm.name());
                saveItems(inv, player);
                saveInvFile(inv, getInventoryFile(player, worldName, gm.name()));
            }
            if (gm.equals(GameMode.ADVENTURE)) {
                YamlConfiguration inv = getInventoryYml(player, worldName, gm.name());
                saveItems(inv, player);
                saveInvFile(inv, getInventoryFile(player, worldName, gm.name()));
            }
            if (gm.equals(GameMode.CREATIVE)) {
                YamlConfiguration inv = getInventoryYml(player, worldName, gm.name());
                saveItems(inv, player);
                saveInvFile(inv, getInventoryFile(player, worldName, gm.name()));
            }
        }
    }
    public boolean loadInventory(Player player, String worldName, GameMode gm) {
        if (Main.plugin.getConfig().getString("Player.Inventory").equalsIgnoreCase("True")) {
            if (gm.equals(GameMode.SURVIVAL)) {
                if (hasInventory(player, worldName, gm.name())) {
                    loadItems(getInventoryYml(player, worldName, gm.name()), player);
                    return true;
                }
            }
            if (gm.equals(GameMode.ADVENTURE)) {
                if (hasInventory(player, worldName, gm.name())) {
                    loadItems(getInventoryYml(player, worldName, gm.name()), player);
                    return true;
                }
            }
            if (gm.equals(GameMode.CREATIVE)) {
                if (hasInventory(player, worldName, gm.name())) {
                    loadItems(getInventoryYml(player, worldName, gm.name()), player);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasInventory(Player player, String worldName, String gamemode) {
        UUID uuid = player.getUniqueId();
        File invFile = new File(Main.plugin.getDataFolder() + "/Data/Players/" + uuid
                + "/Inventories/" + worldName + "_" + gamemode + ".yml");
        return invFile.exists();
    }
    public File getInventoryFile(Player player, String worldName, String gamemode) {
        UUID uuid = player.getUniqueId();
        return new File(Main.plugin.getDataFolder() + "/Data/Players/" + uuid + "/Inventories/" + worldName + "_" + gamemode + ".yml");
    }
    public YamlConfiguration getInventoryYml(Player player, String worldName, String gamemode) {
        return YamlConfiguration.loadConfiguration(getInventoryFile(player, worldName, gamemode));
    }
    public void saveInvFile(YamlConfiguration invYml, File invFile) {
        try {
            invYml.save(invFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void saveItems(YamlConfiguration invFile, Player player) {
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack item = player.getInventory().getItem(i);
            if (item != null) {
                invFile.set("Inv." + i + ".Item", item.getType().name());
                invFile.set("Inv." + i + ".Amount", item.getAmount());
                invFile.set("Inv." + i + ".Damage", item.getDurability());
                if (item.hasItemMeta()) {
                    ItemMeta meta = item.getItemMeta();
                    if (meta.hasDisplayName()) {
                        invFile.set("Inv." + i + ".Meta.DisplayName", meta.getDisplayName());
                    }
                    if (meta.hasLore()) {
                        invFile.set("Inv." + i + ".Meta.Lore", meta.getLore());
                    }
                    if (meta.hasEnchants()) {
                        List<String> enchants = invFile.getStringList("Inv." + i + ".Meta.Enchants.List");
                        for (Enchantment enchant : meta.getEnchants().keySet()) {
                            Integer level = meta.getEnchantLevel(enchant);
                            String name = enchant.getName();
                            enchants.add(name);
                            invFile.set("Inv." + i + ".Meta.Enchants." + name + ".Level", level);
                        }
                        invFile.set("Inv." + i +".Meta.Enchants.List", enchants);
                    }
                }
            } else {
                invFile.set("Inv." + i, null);
            }
        }
    }

    private void loadItems(YamlConfiguration invFile, Player player) {
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (invFile.contains("Inv." + i)) {
                String itemName = invFile.getString("Inv." + i + ".Item");
                Integer amount = invFile.getInt("Inv." + i + ".Amount");
                Short durability = Short.valueOf(invFile.getString("Inv." + i + ".Damage"));

                ItemStack item = new ItemStack(Material.getMaterial(itemName), amount, durability);
                if (invFile.contains("Inv." + i + ".Meta")) {
                    ItemMeta meta = item.getItemMeta();
                    if (invFile.contains("Inv." + i + ".Meta.DisplayName")) {
                        meta.setDisplayName(invFile.getString("Inv." + i + ".Meta.DisplayName"));
                    }
                    if (invFile.contains("Inv." + i + ".Meta.Lore")) {
                        meta.setLore(invFile.getStringList("Inv." + i + ".Meta.Lore"));
                    }
                    if (invFile.contains("Inv." + i + ".Meta.Enchants")) {
                        for (String enchantName : invFile.getStringList("Inv." + i + ".Meta.Enchants.List")) {
                            Integer level = invFile.getInt("Inv." + i + ".Meta.Enchants." + enchantName + ".Level");
                            Enchantment enchant = Enchantment.getByName(enchantName);
                            meta.addEnchant(enchant, level, false);
                        }
                    }
                    item.setItemMeta(meta);
                }
                player.getInventory().setItem(i, item);
            } else {
                player.getInventory().setItem(i, null);
            }
        }
    }
}
