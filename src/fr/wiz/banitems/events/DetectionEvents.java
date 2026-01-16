package fr.wiz.banitems.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.wiz.banitems.utils.BannedItemsManager;


public class DetectionEvents implements Listener {
	
	/** 
	 *  
	 *  Try every possible way to detect a banned items.
	 *  All detection related events are present here.
	 * 
	 * **/
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
	    Inventory inv = e.getPlayer().getInventory();

	    for (int i = 0; i < inv.getSize(); i++) {
	        ItemStack item = inv.getItem(i);

	        if (item == null || item.getType() == Material.AIR) continue;

	        String name;
	        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
	            name = item.getItemMeta().getDisplayName();
	        } else {
	            name = item.getType().name();
	        }

	        System.out.println(name);

	
	        if (BannedItemsManager.isBanned(item)) {
	            inv.remove(item);
	            e.getPlayer().sendMessage(name + " has been removed from your inventory!");
	        }
	    }
	}

}
