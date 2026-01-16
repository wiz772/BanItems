package fr.wiz.banitems.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BannedItemsManager {

    public static List<Material> getBannedItems() {
        return ConfigManager.getBannedItemsCache();
    }


    public static boolean isBanned(ItemStack item) {
        if (item == null) return false;
        return getBannedItems().contains(item.getType()); 
    }
}
