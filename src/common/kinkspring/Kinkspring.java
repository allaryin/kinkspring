package net.minecraft.src;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.MinecraftForgeClient;
import net.minecraft.src.forge.NetworkMod;

import kinkspring.Config;
import kinkspring.blocks.BlockCapacitor;
import kinkspring.blocks.KinkspringBlocks;
import kinkspring.blocks.TileCapacitor;
import kinkspring.integration.BuildcraftIntegrator;

public class Kinkspring extends NetworkMod {
		
	//CLIENT SIDE
	public static boolean initialized = false;
	public static boolean configLoaded = false;
	public static File path = Minecraft.getMinecraftDir();
	private int tickCount;
	
	@Override
	public String getVersion() {
		return Config.getVersion();
	}
	
	@Override
	public String getName() {
		return Config.getName();
	}

	@Override
	public void load() {
		if(!configLoaded && !ModLoader.isModLoaded("mod_Kinkspring")){
			try {
				Config.INSTANCE.init(this.path, "config/kinkspring.cfg");
				preloadTextures();
			} catch (Exception e) {
				e.printStackTrace();
			}
			configLoaded = true;
		}
	}
	
	private void preloadTextures() {
		System.out.println("preloadTextures");
		MinecraftForgeClient.preloadTexture("/textures/blocks.png");
	}
	
	public void modsLoaded() {
		super.modsLoaded();
		registerFirst();
		ModLoader.setInGameHook(this, true, true);
	}
	
	public boolean onTickInGame( float tick, Minecraft mc ) {
		if( !initialized ) {
			if( BuildcraftIntegrator.isAvailable() ) {
				registerLast();
				initialized = true;
				tickCount = 0;
			} else if( tickCount > 25 ) {
				System.out.println("Still waiting for BC...");
			} else {
				tickCount++;
			}
		} else {
			ModLoader.setInGameHook(this, false, false);
		}
		return true;
	}
	
	private void registerFirst() {
		System.out.println("register - blocks");
		KinkspringBlocks.springCapacitor.block = new BlockCapacitor(KinkspringBlocks.springCapacitor.id);
		
		int c = 0;
		for( KinkspringBlocks blocks : KinkspringBlocks.values() ) {
			final Block block = blocks.block;
			if( block != null) {
				++c;
				ModLoader.registerBlock( block );
				if( blocks.name != null ) {
					ModLoader.addName(block, blocks.name);
				}
			}
		}
		System.out.println("registerd "+c+" blocks");
		
		System.out.println("register - tiles");
		ModLoader.registerTileEntity(TileCapacitor.class, "kinkspring.capacitor");
	}
	
	private void registerLast() {
		if( !BuildcraftIntegrator.isAvailable() ) {
			throw new RuntimeException("Buildcraft 2 is required to run Kinkspring.");
		}
		BuildcraftIntegrator.initialize();
		
		// register recipe
		System.out.println("register - recipes");
		ModLoader.addRecipe(new ItemStack(KinkspringBlocks.springCapacitor.block,1), new Object[] {
			"XPG", "III", "WPX",
			Character.valueOf('X'), new ItemStack(BuildcraftIntegrator.ironGear,1),
			Character.valueOf('P'), new ItemStack(Block.pistonBase,1),
			Character.valueOf('G'), new ItemStack(BuildcraftIntegrator.pipePowerGold,1),
			Character.valueOf('W'), new ItemStack(BuildcraftIntegrator.pipePowerWood,1),
			Character.valueOf('I'), new ItemStack(Item.ingotIron,1)
		});
	}
	
	public boolean clientSideRequired() {
		return true;
	}

	public boolean serverSideRequired() {
		return false;
	}
	
}
