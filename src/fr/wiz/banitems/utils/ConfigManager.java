package fr.wiz.banitems.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import fr.wiz.banitems.Main;

public class ConfigManager {

    private static List<Material> bannedItemsCache = new ArrayList<>();

    public static boolean reloadConfig() {
        try {
            Main.getInstance().reloadConfig();
            loadBannedItems();
            Main.getInstance().getLogger().info("Configuration reloaded!");
            return true;
        } catch (Exception e) {
            Main.getInstance().getLogger().severe(
                "Configuration reload failed! Error: " + e.getMessage()
            );
            return false;
        }
    }


    public static void saveConfig() {
        try {
            Main.getInstance().saveConfig();
            Main.getInstance().getLogger().info("Configuration saved!");
        } catch(Exception e) {
            Main.getInstance().getLogger().severe("Configuration save failed! Error: " + e.getMessage());
        }
    }
    
    public static String getRemovalMessage() {
        return Main.getInstance().getConfig()
                .getString("messages.removal", "§c%item% has been removed from your inventory!");
    }

    public static void loadBannedItems() {
        bannedItemsCache.clear();
        List<String> bannedItems = Main.getInstance().getConfig().getStringList("banned_items");
        for (String itemName : bannedItems) {
            Material mat = Material.getMaterial(itemName.toUpperCase());
            if (mat != null) bannedItemsCache.add(mat);
        }
    }

    public static List<Material> getBannedItemsCache() {
        return new ArrayList<>(bannedItemsCache);
    }
}
