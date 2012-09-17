package kinkspring;

import java.io.File;

import kinkspring.blocks.KinkspringBlocks;

import net.minecraft.src.Block;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.Property;

public enum Config {
	INSTANCE;

	private static final String version = "0.1";
	private static final String name = "Kinkspring";

	private Configuration _conf;
	private Config() {
		_conf = null;
	}

	public void init(File minecraftDir, String path) throws Exception {
		if( _conf != null ) {
			throw new Exception("Attempt to re-initialize.");
		}

		final File file;
		if (minecraftDir != null) {
			file = new File(minecraftDir, path);
		} else {
			file = new File(path);
		}		

		_conf = new Configuration(file);
		_conf.load();
		populateDefaults();
		save();
	}
	
	private void populateDefaults() {
		Property prop = _conf.getOrCreateIntProperty( "capacitor", Configuration.CATEGORY_BLOCK, 1234 );
		KinkspringBlocks.springCapacitor.id = prop.getInt();
		KinkspringBlocks.springCapacitor.name = "kinkspring.capacitor";
	}	

	public static String getVersion() {
		return version;
	}

	public static String getName() {
		return name;
	}
	
	public void save() {
		Property versionProp = _conf.getOrCreateProperty("version", Configuration.CATEGORY_GENERAL, "0.0");
		versionProp.value = getVersion();
		_conf.save();
		System.out.println("wrote config "+getVersion());
	}
	
}
