package com.allaryin.kinkspring.lib;

import java.io.File;

import net.minecraft.block.material.Material;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import com.allaryin.kinkspring.Kinkspring;
import com.allaryin.kinkspring.block.BlockCapacitor;
import com.allaryin.kinkspring.block.Blocks;
import com.allaryin.kinkspring.block.KBlock;
import com.allaryin.kinkspring.tile.KTileEntity;
import com.allaryin.kinkspring.tile.TileCapacitor;

import cpw.mods.fml.common.registry.GameRegistry;

public enum Config {
	INSTANCE;

	public static final int	DEFAULT_BLOCK_PREFIX	= 1850;
	public static final int	DEFAULT_ITEM_PREFIX		= 18550;

	public static int		BLOCK_PREFIX			= DEFAULT_BLOCK_PREFIX;
	public static int		ITEM_PREFIX				= DEFAULT_ITEM_PREFIX;

	private Configuration _conf;
	private Config() {
		_conf = null;
	}

	public static void init(File minecraftDir, String path) throws Exception {
		if (INSTANCE._conf != null) {
			throw new Exception("Attempt to re-initialize.");
		}
		INSTANCE._init(minecraftDir, path);
	}

	private void _init(File minecraftDir, String path) {
		final File file;
		if (minecraftDir != null) {
			file = new File(minecraftDir, path);
		} else {
			file = new File(path);
		}		

		_conf = new Configuration(file);
		_conf.load();

		BLOCK_PREFIX = _conf.get(Configuration.CATEGORY_BLOCK, "prefix",
				DEFAULT_BLOCK_PREFIX).getInt();
		ITEM_PREFIX = _conf.get(Configuration.CATEGORY_ITEM, "prefix",
				DEFAULT_ITEM_PREFIX).getInt();

		initItems();
		initBlocks();
		initRecipes();
	}

	private void initItems() {
		Kinkspring.log.info("Initializing items...");
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

		Property propID = _conf.get(Configuration.CATEGORY_BLOCK, name + ".id",
				BLOCK_PREFIX + bid);

		KBlock block;
		block = new KBlock(propID.getInt(), bid, Material.rock);
		if (tileClass != null) {
			block.setTileClass(tileClass);
		}
		blockEnum.setBlock(block);

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
