package kinkspring;

import java.io.File;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import kinkspring.blocks.KinkspringBlocks;

public enum Config {
	INSTANCE;

	public static final String VERSION = "0.1.1";
	public static final String NAME = "Kinkspring";

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
		Property prop = _conf.get( "capacitor", Configuration.CATEGORY_BLOCK, 1234 );
		KinkspringBlocks.springCapacitor.id = prop.getInt();
		KinkspringBlocks.springCapacitor.name = "kinkspring.capacitor";
	}	
	
	public void save() {
		Property versionProp = _conf.get("version", Configuration.CATEGORY_GENERAL, "0.0");
		versionProp.set(VERSION);
		_conf.save();
		System.out.println("wrote config "+VERSION);
	}
	
}
