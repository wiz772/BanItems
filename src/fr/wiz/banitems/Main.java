package fr.wiz.banitems;

import org.bukkit.plugin.java.JavaPlugin;

import fr.wiz.banitems.events.DetectionEvents;
import fr.wiz.banitems.utils.ConfigManager;

public class Main extends JavaPlugin {
	
	public static Main instance;
	
	@Override
	public void onEnable() {	
		instance = this;
		
		init();
		
		getLogger().info("Initialized!");		

	}
	
	public static Main getInstance() {
		return instance;
	}
	
	
	// Initializing events/commands/config
	
	private void init() {
		registerEvents();
		registerCommands();
		
		saveDefaultConfig();
		ConfigManager.loadBannedItems();
	}
	
	private void registerCommands() {
		
	}

	private void registerEvents() {
	    getServer().getPluginManager().registerEvents(new DetectionEvents(), this);
	}
	


}
