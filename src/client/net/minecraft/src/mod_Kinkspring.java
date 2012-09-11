package net.minecraft.src;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.MinecraftForgeClient;
import net.minecraft.src.forge.NetworkMod;

import kinkspring.Config;
import kinkspring.blocks.BlockCapacitor;
import kinkspring.blocks.KinkspringBlocks;

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
		}
		preloadTextures();
		registerBlocks();
	}
	
	private void preloadTextures() {
		System.out.println("preloadTextures");
		MinecraftForgeClient.preloadTexture("/kinkspring/textures/blocks.png");
	}
	
	private void registerBlocks() {
		System.out.println("registerBlocks");
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
		
	}
	
	public boolean clientSideRequired() {
		return true;
	}

	public boolean serverSideRequired() {
		return false;
	}
	
}
