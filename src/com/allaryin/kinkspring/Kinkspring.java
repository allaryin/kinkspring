package com.allaryin.kinkspring;

import java.util.logging.Logger;

import com.allaryin.kinkspring.lib.Config;
import com.allaryin.kinkspring.lib.Version;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(name = Version.MOD_NAME, version = Version.VERSION, modid = Version.MOD_ID, useMetadata = false, acceptedMinecraftVersions = "[1.6,1.7)", dependencies = "required-after:Forge@[9.10.0.800,)")

public class Kinkspring {
	@Instance(Version.CHANNEL)
	public static Kinkspring	instance;

	public static Logger		log	= Logger.getLogger(Version.CHANNEL);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		log.setParent(FMLLog.getLogger());
		log.info(Version.MOD_NAME + " - v" + Version.VERSION);

		// load config
		try {
			Config.init(Loader.instance().getConfigDir(), Version.CHANNEL
					+ ".cfg");
		} catch (Exception e) {
			e.printStackTrace();
			log.warning("Unable to init config file.");
		}

		// TODO: register block id's
		// TODO: register item id's
		Config.save();

		// TODO: proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// TODO: init localization
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// TODO: announce to submods
	}
}
