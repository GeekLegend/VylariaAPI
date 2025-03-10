package fr.vylaria.api.builders;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

	private ItemStack is;

	public ItemBuilder(Material m) {
		this(m, 1);
	}

	public ItemBuilder(ItemStack is) {
		this.is = is;
	}

	public ItemBuilder(Material m, int amount) {
		this.is = new ItemStack(m, amount);
	}

	public ItemBuilder(Material m, int amount, byte durability) {
		this.is = new ItemStack(m, amount, durability);
	}

	public ItemBuilder clone() {
		return new ItemBuilder(this.is);
	}

	public ItemBuilder setDurability(short dur) {
		this.is.setDurability(dur);
		return this;
	}

	public ItemBuilder setName(String name) {
		ItemMeta im = this.is.getItemMeta();
		im.setDisplayName(name);
		this.is.setItemMeta(im);
		return this;
	}
	
	public ItemBuilder addStoredEnchantment(Enchantment ench, int level) {
		ItemMeta im = this.is.getItemMeta();
		((EnchantmentStorageMeta) im).addStoredEnchant(ench, level, true);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
		this.is.addUnsafeEnchantment(ench, level);
		return this;
	}

	public ItemBuilder removeEnchantment(Enchantment ench) {
		this.is.removeEnchantment(ench);
		return this;
	}

	public ItemBuilder setSkullOwner(String owner) {
		try {
			SkullMeta im = (SkullMeta) this.is.getItemMeta();
			im.setOwner(owner);
			this.is.setItemMeta(im);
		} catch (ClassCastException localClassCastException) {
		}
		return this;
	}

	public ItemBuilder addEnchant(Enchantment ench, int level) {
		ItemMeta im = this.is.getItemMeta();
		im.addEnchant(ench, level, true);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
		this.is.addEnchantments(enchantments);
		return this;
	}

	public ItemBuilder setInfinityDurability() {
		this.is.setDurability((short) Short.MAX_VALUE);
		return this;
	}

	public ItemBuilder unbreakable(boolean unbreakable) {
		ItemMeta im = this.is.getItemMeta();
		im.spigot().setUnbreakable(unbreakable);
		return this;
	}

	public ItemBuilder setGlowing() {
		try {
			ItemMeta meta = this.is.getItemMeta();
			meta.addEnchant(Enchantment.DURABILITY, 0, true);
			meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
			this.is.setItemMeta(meta);
		} catch (Exception localException) {
		}
		return this;
	}

	public ItemBuilder removePotionLore() {
		try {
			ItemMeta meta = this.is.getItemMeta();
			meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
			this.is.setItemMeta(meta);
		} catch (Exception localException) {
		}
		return this;
	}

	public ItemBuilder setLore(String... lore) {
		ItemMeta im = this.is.getItemMeta();
		im.setLore(Arrays.asList(lore));
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder setLore(List<String> lore) {
		ItemMeta im = this.is.getItemMeta();
		im.setLore(lore);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder removeLoreLine(String line) {
		ItemMeta im = this.is.getItemMeta();
		List<String> lore = new ArrayList<String>(im.getLore());
		if (!lore.contains(line)) {
			return this;
		}
		lore.remove(line);
		im.setLore(lore);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder removeLoreLine(int index) {
		ItemMeta im = this.is.getItemMeta();
		List<String> lore = new ArrayList<String>(im.getLore());
		if ((index < 0) || (index > lore.size())) {
			return this;
		}
		lore.remove(index);
		im.setLore(lore);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder addLoreLine(String line) {
		ItemMeta im = this.is.getItemMeta();
		List<String> lore = new ArrayList<String>();
		if (im.hasLore()) {
			lore = new ArrayList<String>(im.getLore());
		}
		lore.add(line);
		im.setLore(lore);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder addLoreLine(String line, int pos) {
		ItemMeta im = this.is.getItemMeta();
		List<String> lore = new ArrayList<String>(im.getLore());
		lore.set(pos, line);
		im.setLore(lore);
		this.is.setItemMeta(im);
		return this;
	}

	public ItemBuilder setDyeColor(DyeColor color) {
		this.is.setDurability(color.getData());
		return this;
	}

	@Deprecated
	public ItemBuilder setWoolColor(DyeColor color) {
		if (!this.is.getType().equals(Material.WOOL)) {
			return this;
		}
		this.is.setDurability(color.getData());
		return this;
	}

	public ItemBuilder setLeatherArmorColor(Color color) {
		try {
			LeatherArmorMeta im = (LeatherArmorMeta) this.is.getItemMeta();
			im.setColor(color);
			this.is.setItemMeta(im);
		} catch (ClassCastException localClassCastException) {
		}
		return this;
	}

	public ItemStack toItemStack() {
		return this.is;
	}
}