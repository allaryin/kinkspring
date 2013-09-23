package com.allaryin.kinkspring.lib;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import com.allaryin.kinkspring.Kinkspring;
import com.allaryin.kinkspring.block.BlockCapacitor;
import com.allaryin.kinkspring.block.Blocks;
import com.allaryin.kinkspring.block.KBlock;
import com.allaryin.kinkspring.item.ItemSpring;
import com.allaryin.kinkspring.item.Items;
import com.allaryin.kinkspring.item.KItem;
import com.allaryin.kinkspring.tile.KTileEntity;
import com.allaryin.kinkspring.tile.TileCapacitor;

import cpw.mods.fml.common.registry.GameRegistry;

public enum Config {
	INSTANCE;

	public static final int	DEFAULT_BLOCK_PREFIX	= 1850;
	public static final int	DEFAULT_ITEM_PREFIX		= 18550;

	public static int		BLOCK_PREFIX			= DEFAULT_BLOCK_PREFIX;
	public static int		ITEM_PREFIX				= DEFAULT_ITEM_PREFIX;
	
	public static final String	TEXTURE_PATH			= "kinkspring";

	private Configuration _conf;
	private Config() {
		_conf = null;
	}

	public static void init(File file) throws Exception {
		if (INSTANCE._conf != null) {
			throw new Exception("Attempt to re-initialize.");
		}
		INSTANCE._init(file);
	}

	private void _init(File file) {
		_conf = new Configuration(file);
		_conf.load();

		BLOCK_PREFIX = _conf.get(Configuration.CATEGORY_GENERAL, "block.prefix",
				DEFAULT_BLOCK_PREFIX).getInt();
		ITEM_PREFIX = _conf.get(Configuration.CATEGORY_GENERAL, "item.prefix",
				DEFAULT_ITEM_PREFIX).getInt();

		initItems();
		initBlocks();
		initRecipes();
	}

	private void initItems() {
		Kinkspring.log.info("Initializing items...");
		
		initItem(Items.spring, ItemSpring.class);
		
		Kinkspring.log.fine("Items done.");
	}
	
	private void initBlocks() {
		Kinkspring.log.info("Initializing blocks...");
		
		initBlock(Blocks.springCapacitor, BlockCapacitor.class,
				TileCapacitor.class);
		
		Kinkspring.log.fine("Blocks done.");
	}	
	
	private void initRecipes() {
		Kinkspring.log.info("Initializing recipes...");
		Kinkspring.log.fine("Recipes done.");
	}
	
	private void initItem(Items itemEnum,
			Class<? extends KItem> itemClass) {
		final int iid = itemEnum.iid;
		final String name = itemEnum.name();

		final Property propEnabled = _conf.get(Configuration.CATEGORY_ITEM, name
				+ ".enabled", true);
		if (!propEnabled.getBoolean(true)) {
			Kinkspring.log.fine("Skipping item " + name
					+ ", disabled in config");
			return;
		}

		final Property propID = _conf.getItem(Configuration.CATEGORY_ITEM, name + ".id",
				ITEM_PREFIX + iid);
		// Handle all of the ID offset nonsense
		// NOTE: Do we need to do this? Do we want to?
		final int offsetID = propID.getInt() - 256;

		final KItem item;
		try {
			item = itemClass.getConstructor(Integer.class, Integer.class)
					.newInstance(offsetID, iid);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			Kinkspring.log.log(Level.SEVERE, "Unable to instantiate "+name,e);
			return;
		}
		itemEnum.setItem(item);
		item.setEnumEntry(itemEnum);

		// TODO: id conflict resolution & reservation
		GameRegistry.registerItem(item, Version.MOD_ID + name);

		Kinkspring.log.fine("Initialized item " + name);
	}

	private void initBlock(Blocks blockEnum,
			Class<? extends KBlock> blockClass,
			Class<? extends KTileEntity> tileClass) {
		final int bid = blockEnum.bid;
		final String name = blockEnum.name();

		Property propEnabled = _conf.get(Configuration.CATEGORY_BLOCK, name
				+ ".enabled", true);
		if (!propEnabled.getBoolean(true)) {
			Kinkspring.log.fine("Skipping block " + name
					+ ", disabled in config");
			return;
		}

		Property propID = _conf.getBlock(Configuration.CATEGORY_BLOCK, name + ".id",
				BLOCK_PREFIX + bid);

		KBlock block;
		try {
			block = blockClass.getConstructor(Integer.class, Integer.class)
					.newInstance(propID.getInt(), bid);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			Kinkspring.log
					.log(Level.SEVERE, "Unable to instantiate " + name, e);
			return;
		}

		if (tileClass != null) {
			block.setTileClass(tileClass);
		}
		blockEnum.setBlock(block);
		block.setEnumEntry(blockEnum);

		// TODO: id conflict resolution & reservation
		GameRegistry.registerBlock(block, Version.MOD_ID + name);

		Kinkspring.log.fine("Initialized block " + name);
	}

	public static void save() {
		INSTANCE._conf.get(Configuration.CATEGORY_GENERAL, "version",
				Version.VERSION);
		INSTANCE._conf.save();
	}
	
}
