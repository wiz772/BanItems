package fr.wiz.banitems.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.wiz.banitems.utils.ConfigManager;

public class BanItemsCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(!sender.hasPermission("banitems.reload")) {
			sender.sendMessage("§c[ERROR] You don't have the permission to execute this command.");
			return true;
		}
		
		
		if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
			if(ConfigManager.reloadConfig()) {
				sender.sendMessage("§a[SUCCESS] BanItems config reloaded!");
			}else {
				sender.sendMessage("§c[ERROR] BanItems configuration relooad failed! Check console for more details.");
			}
			return true;
		}
		
		sender.sendMessage("§c[ERROR] Usage /banitems reload");

		return false;
	}

}
