package com.allaryin.kinkspring.lib;

import java.util.Properties;

import net.minecraft.client.Minecraft;

import com.allaryin.kinkspring.block.Blocks;
import com.allaryin.kinkspring.block.KBlock;
import com.allaryin.kinkspring.item.Items;
import com.allaryin.kinkspring.item.KItem;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class Localization {
	private static Localization INSTANCE;
	public static String LOCALE;
	public static final String NS = "kinkspring.";

	private Properties props;
	
	public static void init() throws Exception {
		if( INSTANCE != null ) {
			throw new Exception("Attempt to re-initialize.");
		}
		INSTANCE = new Localization();
		
		LOCALE = Minecraft.getMinecraft().gameSettings.language;
		
		INSTANCE.loadDefaults();
		// TODO: read real loc strings from props file
		
		INSTANCE.localizeBlocks();
		INSTANCE.localizeItems();
	}
	
	private void loadDefaults() {
		props = new Properties();
		props.setProperty("item.spring",			"Kinkspring");
		props.setProperty("block.springCapacitor",	"Kinkspring Capacitor");
	}

	private void localizeBlocks() {
		final Blocks[] blocks = Blocks.values();
		for (int k = 0; k < blocks.length; ++k) {
			final KBlock block = blocks[k].block;
			final String key = "block." + blocks[k].name();
			LanguageRegistry.addName(block, props.getProperty(key));
		}
	}
	
	private void localizeItems() {
		final Items[] items = Items.values();
		for (int k = 0; k < items.length; ++k) {
			final KItem item = items[k].item;
			final String key = "item." + items[k].name();
			LanguageRegistry.addName(item, props.getProperty(key));
		}
	}
}
