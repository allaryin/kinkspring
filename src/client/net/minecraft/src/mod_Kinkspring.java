package net.minecraft.src;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.MinecraftForgeClient;
import net.minecraft.src.forge.NetworkMod;

import kinkspring.Config;

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
			preloadTextures();
		}
	}
	
	public void preloadTextures(){
		// MinecraftForgeClient.preloadTexture("/kinkspring/textures/blocks.png");
	}
	
	public boolean clientSideRequired() {
		return true;
	}

	public boolean serverSideRequired() {
		return false;
	}
	
}
