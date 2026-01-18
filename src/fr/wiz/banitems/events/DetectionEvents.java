package fr.wiz.banitems.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.wiz.banitems.utils.BannedItemsManager;

public class DetectionEvents implements Listener {


    


	/** 
	 * 
	 * Try every possible way to detect a banned items. 
	 * All detection related events are present here. 
	 *
	**/

	@EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Inventory inv = e.getPlayer().getInventory();
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (BannedItemsManager.handleBannedItem(e.getPlayer(), item)) {
                inv.setItem(i, null);
            }
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem().getItemStack();
        if (BannedItemsManager.handleBannedItem(player, item)) {
            e.setCancelled(true);
            e.getItem().remove();
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItemDrop().getItemStack();
        if (BannedItemsManager.handleBannedItem(player, item)) {
            e.setCancelled(true);
            e.getItemDrop().remove();
        }
    }

    @SuppressWarnings("deprecation")
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player player = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();
        ItemStack cursor = e.getCursor();
        if (BannedItemsManager.handleBannedItem(player, current)) {
            e.setCancelled(true);
            e.setCurrentItem(null);
        } else if (BannedItemsManager.handleBannedItem(player, cursor)) {
            e.setCancelled(true);
            e.setCursor(null);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player player = (Player) e.getWhoClicked();
        ItemStack result = e.getRecipe().getResult();
        if (BannedItemsManager.handleBannedItem(player, result)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        if (BannedItemsManager.handleBannedItem(player, item)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();
        if (item != null && BannedItemsManager.handleBannedItem(player, item)) {
            e.setCancelled(true);
            player.getInventory().setItemInHand(null);
        }
    }

    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        ItemStack newItem = player.getInventory().getItem(e.getNewSlot());
        if (BannedItemsManager.handleBannedItem(player, newItem)) {
            player.getInventory().setItem(e.getNewSlot(), null);
        }
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getKiller() == null) return;
        Player joueur = e.getEntity().getKiller();
        for (ItemStack loot : e.getDrops()) {
            if (BannedItemsManager.handleBannedItem(joueur, loot)) {
                e.getDrops().remove(loot);
            }
        }
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player joueur = e.getPlayer();
        for (ItemStack drop : e.getBlock().getDrops()) {
            if (BannedItemsManager.handleBannedItem(joueur, drop)) {
                drop.setAmount(0);
            }
        }
    }

}
