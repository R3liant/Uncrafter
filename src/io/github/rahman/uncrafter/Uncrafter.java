package io.github.rahman.uncrafter;

import java.util.ArrayList;
//import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.plugin.java.JavaPlugin;

/* TODO
 Add config pull
 Add enchantment removal
 Proper error messages
 */
public class Uncrafter extends JavaPlugin {
	public final String loggerPrefix = "[Uncrafter] ";
	public final String chatPrefix = ChatColor.WHITE + "[" + ChatColor.BLUE
			+ "Uncrafter" + ChatColor.WHITE + "] ";

	// public List<String> blacklisted;

	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		getLogger().info(
				loggerPrefix + "- Version "
						+ this.getDescription().getVersion()
						+ " has been enabled.");
	}

	public boolean onCommand(final CommandSender sender, Command command,
			String label, String[] args) {
		if (command.getName().equalsIgnoreCase("unc")
				& (sender.hasPermission("uncrafter.all"))) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack item = player.getPlayer().getItemInHand();
				if (item == null) {
					return false;
				}
				if (item.getAmount() > 1) {
					// int multiplier = item.getAmount();
					sender.sendMessage(chatPrefix
							+ ChatColor.RED
							+ "You tried to uncraft too many items! The maximum is one at a time.");
					return true;
				}
				Recipe recipe = getServer().getRecipesFor(
						player.getItemInHand()).get(0);
				if (recipe == null) {
					return false;
				}

				if (item.getDurability() == 0) {
					sender.sendMessage(chatPrefix + ChatColor.RED
							+ "Durabillity Correct!");
					if (recipe instanceof ShapelessRecipe) {
						ArrayList<ItemStack> slist = (ArrayList<ItemStack>) ((ShapelessRecipe) recipe)
								.getIngredientList();
						if (item.getEnchantments().size() >= 1) {
							Map<Enchantment, Integer> stored = item
									.getEnchantments();
							ItemStack resultStack = new ItemStack(
									Material.ENCHANTED_BOOK);
							EnchantmentStorageMeta em = (EnchantmentStorageMeta) resultStack
									.getItemMeta();
							for (Entry<Enchantment, Integer> e : stored
									.entrySet()) {
								em.addStoredEnchant(e.getKey(), e.getValue(),
										true);
							}
							resultStack.setItemMeta(em);
							player.getInventory().addItem(resultStack);

							player.getInventory().setItemInHand(
									new ItemStack(Material.AIR));
							sender.sendMessage(chatPrefix + ChatColor.RED
									+ "Your item has been uncrafted!");
							for (ItemStack aStack : slist) {
								player.getInventory().addItem(aStack);
							}

						}
						return true;
					} else if (recipe instanceof ShapedRecipe) {
						Map<Character, ItemStack> shapedmap = ((ShapedRecipe) recipe)
								.getIngredientMap();
						if (item.getEnchantments().size() >= 1) {
							Map<Enchantment, Integer> stored = item
									.getEnchantments();
							ItemStack resultStack = new ItemStack(
									Material.ENCHANTED_BOOK);
							EnchantmentStorageMeta em = (EnchantmentStorageMeta) resultStack
									.getItemMeta();
							for (Entry<Enchantment, Integer> e : stored
									.entrySet()) {
								em.addStoredEnchant(e.getKey(), e.getValue(),
										true);
							}
							resultStack.setItemMeta(em);
							player.getInventory().addItem(resultStack);
						}
						player.getInventory().setItemInHand(
								new ItemStack(Material.AIR));
						sender.sendMessage(chatPrefix + ChatColor.RED
								+ "Your item has been uncrafted!");
						for (ItemStack shapeditemstack : shapedmap.values()) {
							if (shapeditemstack != null) {
								player.getInventory().addItem(shapeditemstack);
							}
						}

					} else {
						player.getInventory().setItemInHand(
								new ItemStack(Material.AIR));
						sender.sendMessage(chatPrefix + ChatColor.RED
								+ "Your item has been uncrafted!");
					}
				} else {
					sender.sendMessage(chatPrefix
							+ ChatColor.RED
							+ "Item is damaged! You must repair it before you try to uncraft!");
					return true;
				}
				return true;
			}
			return true;
		}
		return false;
	}

}