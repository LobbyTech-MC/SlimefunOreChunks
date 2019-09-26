package io.github.thebusybiscuit.slimefunorechunks;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.GEO.OreGenResource;
import me.mrCookieSlime.Slimefun.GEO.OreGenSystem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunMachine;

public class OreChunk extends SlimefunItem implements OreGenResource {

	private int amplifier;
	private String name;
	private ItemStack dust;
	
	public OreChunk(Category category, String id, String name, int amplifier, String texture, ItemStack dust) throws Exception {
		super(category, new CustomItem(CustomSkull.getItem(texture), "&r" + name, "&7Use an Ore Crusher to turn this into Dust"), id, new RecipeType(SlimefunItems.GEO_MINER), new ItemStack[0]);
		
		this.amplifier = amplifier;
		this.name = name;
		this.dust = dust;
		
		OreGenSystem.registerResource(this);
		register();
	}
	
	@Override
	public void postRegister() {
		SlimefunMachine machine = (SlimefunMachine) RecipeType.ORE_CRUSHER.getMachine();
		
		if (!machine.isDisabled()) {
			machine.addRecipe(new ItemStack[] {getItem()}, dust);
		}
	}

	@Override
	public int getDefaultSupply(Biome biome) {
		if (biome == Biome.NETHER) return 0;
		if (biome == Biome.THE_END) return 0;
		if (biome == Biome.END_BARRENS) return 0;
		if (biome == Biome.END_HIGHLANDS) return 0;
		if (biome == Biome.END_MIDLANDS) return 0;
		if (biome == Biome.SMALL_END_ISLANDS) return 0;
		
		return ThreadLocalRandom.current().nextInt(amplifier * 2, 20 + amplifier * 4);
	}

	@Override
	public ItemStack getIcon() {
		return getItem();
	}

	@Override
	public String getMeasurementUnit() {
		return "Chunk(s)";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isLiquid() {
		return false;
	}

}