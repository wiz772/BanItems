package fr.wiz.banitems.utils;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BannedItemsManager {
	
    public static List<Material> getBannedItems() {
        return ConfigManager.getBannedItemsCache();
    }


    public static boolean isBanned(ItemStack item) {
        if (item == null) return false;
        return getBannedItems().contains(item.getType()); 
    }
    
    public static boolean handleBannedItem(Player player, ItemStack item) {
        if (item == null || item.getType() == Material.AIR) return false;
        
        if (player.isOp() || player.getGameMode() != GameMode.SURVIVAL || player.hasPermission("banitems.bypass")) return false;

        
        if (BannedItemsManager.isBanned(item)) {
        	sendRemovalMessage(player, item);
            item.setAmount(0);
            return true;
        }
        return false;
    }
    
    

    public static void sendRemovalMessage(Player player, ItemStack item) {
        String name;

        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            name = item.getItemMeta().getDisplayName();
        } else {
            name = item.getType().name();
        }

        String msg = ConfigManager.getRemovalMessage().replace("%item%", name);
        player.sendMessage(msg);
    }

    
}
