package io.github.rahman.uncrafter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Uncrafter extends JavaPlugin {
	public final String loggerPrefix = "[Uncrafter] ";
	
	public void onEnable(){
		getLogger().info(loggerPrefix + "- Version " + this.getDescription().getVersion() + " has been enabled.");
	}

	public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("unc") & (sender.hasPermission("uncrafter.all"))) {
            	ItemStack item = ((Player) sender).getPlayer().getItemInHand();
            	
            	   if(item==null){
            		   return false;
            	   }
            	   
            	   double dura = ((Player) sender).getPlayer().getItemInHand().getDurability();
            	   
            	   
            	   if(dura == 0){
            		   sender.sendMessage(ChatColor.WHITE + "Uncrafted");
            		   return true;
            	   }
            	   
            }
                return true;
            }
		return false;
            
	}
}
