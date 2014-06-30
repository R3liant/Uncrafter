package io.github.rahman.uncrafter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Uncrafter extends JavaPlugin {
	public final String loggerPrefix = "[Uncrafter] ";
	
	public void onEnable(){
		getLogger().info(loggerPrefix + "- Version " + this.getDescription().getVersion() + " has been enabled.");
		//Add config pull
		//getConfig().getList("Resources").get(x);
	}

	public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("unc") & (sender.hasPermission("uncrafter.all"))) {
            	ItemStack item = ((Player) sender).getPlayer().getItemInHand();
            	
            	   if(item==null){
            		   return false;
            	   }
            	   
            	   int dura = ((Player) sender).getPlayer().getItemInHand().getDurability();
            	   Recipe recipe = getServer().getRecipesFor(((Player) sender).getItemInHand()).get(0);
            	   /*getServer().ShapelessRecipe.getIngredientList();
            	     This is where I am having issues*/
            	   
            	   if(dura == 0){
            		   sender.sendMessage(ChatColor.BLUE + "Uncrafted" + recipe);
            		   return true;
            	   }
            	   
            	   if(dura != 0){
            		   sender.sendMessage(ChatColor.BLUE + "Item is damaged! You must repair it before you try to uncraft!" + recipe);
            		   return true;
            	   }
            	   
            	}
                return true;
            }
		return false;
	}
}
