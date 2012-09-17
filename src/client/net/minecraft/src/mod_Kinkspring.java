package net.minecraft.src;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.MinecraftForgeClient;
import net.minecraft.src.forge.NetworkMod;

import kinkspring.Config;
import kinkspring.blocks.BlockCapacitor;
import kinkspring.blocks.KinkspringBlocks;
import kinkspring.blocks.TileCapacitor;

public class mod_Kinkspring extends NetworkMod {
		
	//CLIENT SIDE
	public static boolean initialized = false;
	public static File path = Minecraft.getMinecraftDir();
	
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
		if(!initialized && !ModLoader.isModLoaded("mod_Kinkspring")){
			try {
				Config.INSTANCE.init(this.path, "config/kinkspring.cfg");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			preloadTextures();
			register();
		}
	}
	
	private void preloadTextures() {
		System.out.println("preloadTextures");
		MinecraftForgeClient.preloadTexture("/textures/blocks.png");
	}
	
	private void register() {
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
		
		// register recipe
		System.out.println("register - recipes");
		ModLoader.addRecipe(new ItemStack(KinkspringBlocks.springCapacitor.block,1), new Object[] {
			"XPG", "III", "WPX",
			Character.valueOf('X'), new ItemStack(Block.blockSteel,1),	// iron gear
			Character.valueOf('P'), new ItemStack(Block.pistonBase,1),
			Character.valueOf('G'), new ItemStack(Item.ingotGold,1),	// gold conductive pipe
			Character.valueOf('W'), new ItemStack(Block.wood,1),		// wood conductive pipe
			Character.valueOf('I'), new ItemStack(Item.ingotIron,1)
		});
		
		System.out.println("register - tiles");
		ModLoader.registerTileEntity(TileCapacitor.class, "kinkspring.capacitor");
	}
	
	public boolean clientSideRequired() {
		return true;
	}

	public boolean serverSideRequired() {
		return false;
	}
	
}
