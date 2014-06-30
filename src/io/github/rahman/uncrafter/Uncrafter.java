package io.github.rahman.uncrafter;

import java.awt.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Uncrafter extends JavaPlugin {
	public final String loggerPrefix = "[Uncrafter] ";
	
	public void onEnable(){
		getLogger().info(loggerPrefix + "- Version " + this.getDescription().getVersion() + " has been enabled.");
		/* TODO
		Add config pull
		getConfig().getList("Resources").get(x);
		Add enchantment removal
		Allow for multiple items
		*/
	}

	public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("unc") & (sender.hasPermission("uncrafter.all"))) {
            	ItemStack item = ((Player) sender).getPlayer().getItemInHand();
            	
            	   if(item==null){
            		   return false;
            	   }
            	   
            	   int dura = ((Player) sender).getPlayer().getItemInHand().getDurability();
            	   
            	   if(dura == 0){
            		   
            		   Recipe recipe = getServer().getRecipesFor(((Player) sender).getItemInHand()).get(0);
            		   if (recipe instanceof ShapelessRecipe){
            			   List a = (List) ((ShapelessRecipe) recipe).getIngredientList();
            			   sender.sendMessage("test " + a);
            			   ((Player) sender).getInventory().setItemInHand(new ItemStack(Material.AIR)); 
                		   sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "Shapeless");
            		   }
            		   
            		   else if(recipe instanceof ShapedRecipe){
            			   ((Player) sender).getInventory().setItemInHand(new ItemStack(Material.AIR)); 
                		   sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "Shaped");
            		   }
            		   
            		   else{
            			   ((Player) sender).getInventory().setItemInHand(new ItemStack(Material.AIR)); 
                		   sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "ERROR");
            		   }
            		   return true;
            	   }
            	   
            	   if(dura != 0){
            		   sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "Item is damaged! You must repair it before you try to uncraft!");
            		   return true;
            	   }
            	   
            	}
                return true;
            }
		return false;
	}
}
