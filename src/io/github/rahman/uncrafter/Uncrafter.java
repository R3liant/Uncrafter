package io.github.rahman.uncrafter;

import java.util.ArrayList;
import java.util.Map;

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
		Proper error messages
		*/
	}

	public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("unc") & (sender.hasPermission("uncrafter.all"))) {
            if (sender instanceof Player) {
            	Player player = (Player) sender;
            	ItemStack item = player.getPlayer().getItemInHand();
            	if(item==null){
                    return false;
            	}
            	Recipe recipe = getServer().getRecipesFor(player.getItemInHand()).get(0);
            	if(recipe == null) {
                    return false;
                }
            	if(player.getPlayer().getItemInHand().getDurability() == 0) {
                    if (recipe instanceof ShapelessRecipe) {
                        ArrayList<ItemStack> slist = (ArrayList<ItemStack>) ((ShapelessRecipe) recipe).getIngredientList();
                        player.getInventory().setItemInHand(new ItemStack(Material.AIR));
                        sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "Your item has been uncrafted!");
                        for (ItemStack aStack : slist) {
                            player.getInventory().addItem(aStack);
                        }
                    } else if (recipe instanceof ShapedRecipe) {
                        Map<Character, ItemStack> shapedmap = ((ShapedRecipe) recipe).getIngredientMap();
                        player.getInventory().setItemInHand(new ItemStack(Material.AIR));
                        sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "Your item has been uncrafted!");
                        for (ItemStack shapeditemstack : shapedmap.values()) {
                            if (shapeditemstack != null) {
                                player.getInventory().addItem(shapeditemstack);
                            }
                        }
                    } else {
                        player.getInventory().setItemInHand(new ItemStack(Material.AIR));
                        sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "Your item has been uncrafted!");
                    }
                    return true;
                } else {
                    sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.BLUE + "Uncrafter" + ChatColor.WHITE + "] " + ChatColor.RED + "Item is damaged! You must repair it before you try to uncraft!");
                    return true;
                }
            }
            return true;
        }
        return true;
	}
}
