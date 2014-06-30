package io.github.rahman.uncrafter;

import java.util.ArrayList;
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
            			   
            			   ArrayList<ItemStack> list = (ArrayList<ItemStack>) ((ShapelessRecipe) recipe).getIngredientList();
            			   ((Player) sender).getInventory().setItemInHand(new ItemStack(Material.AIR));
            			   for( int x=0;x<list.size();x++){
            				   ((Player) sender).getInventory().addItem(list.get(x));
            			   }
            			   
                		   sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "Your item has been uncrafted!");
            		   }
            		   
            		   else if(recipe instanceof ShapedRecipe){
            			   ((Player) sender).getInventory().setItemInHand(new ItemStack(Material.AIR)); 
                		   sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "Your item has been uncrafted!");

            		   }
            		   
            		   else{
            			   ((Player) sender).getInventory().setItemInHand(new ItemStack(Material.AIR)); 
                		   sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "Your item has been uncrafted!");

            		   }
            		   return true;
            	   }
            	   
            	   if(dura != 0){
            		   sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "Item is damaged! You must repair it before you try to uncraft!");
            		   return true;
            	   }
            	   else{
            		   sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "You cannot uncraft this item!");
            	   }
            	}
                return true;
            }
		return false;
	}
}
