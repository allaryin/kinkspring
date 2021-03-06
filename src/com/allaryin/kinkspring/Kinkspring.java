package com.allaryin.kinkspring;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.allaryin.kinkspring.lib.Config;
import com.allaryin.kinkspring.lib.Localization;
import com.allaryin.kinkspring.lib.Version;
import com.allaryin.kinkspring.net.CommonProxy;
import com.allaryin.kinkspring.net.KPacketHandler;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(name = Version.MOD_NAME, version = Version.VERSION, modid = Version.MOD_ID, useMetadata = false, acceptedMinecraftVersions = "[1.6,1.7)", dependencies = "required-after:Forge@[9.11.1.953,)")
@NetworkMod(channels = { Version.CHANNEL }, clientSideRequired = true, serverSideRequired = false, packetHandler = KPacketHandler.class)
public class Kinkspring {
	@Instance(Version.CHANNEL)
	public static Kinkspring	instance;

	@SidedProxy(clientSide = "com.allaryin.kinkspring.net.ClientProxy", serverSide = "com.allaryin.kinkspring.net.CommonProxy")
	public static CommonProxy	proxy;

	public static Logger		log	= Logger.getLogger(Version.CHANNEL);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		log.setParent(FMLLog.getLogger());
		log.info(Version.MOD_NAME + " - v" + Version.VERSION);

		try {
			/**
			 * This is going to read the config file from disk, register block
			 * and item id's, and write the result back out.
			 */
			Config.preInit(event.getSuggestedConfigurationFile());
		} catch (Exception e) {
			log.log(Level.SEVERE, "Unable to load config file...", e);
		}

		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		try {
			Localization.init();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Unable to initialize localization...", e);
		}

		/**
		 * This is going to register all recipes.
		 */
		Config.init();

		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// TODO: announce to submods
	}
}
