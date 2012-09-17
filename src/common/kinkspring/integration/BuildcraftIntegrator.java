package kinkspring.integration;

import net.minecraft.src.Item;
import net.minecraft.src.ModLoader;

public enum BuildcraftIntegrator {
	INSTANCE;
		
	public static Item woodGear;
	public static Item ironGear;
	public static Item pipePowerWood;
	public static Item pipePowerGold;
	
	private static boolean initialized; 
	
	public static boolean isAvailable() {
		return ModLoader.isModLoaded("mod_BuildCraftCore") && ModLoader.isModLoaded("mod_BuildCraftTransport");
	} 
	
	public static void initialize() {
		if( initialized || !isAvailable() ) {
			return;
		}
		
		woodGear = INSTANCE.initItem("BuildCraftCore","woodenGearItem");
		ironGear = INSTANCE.initItem("BuildCraftCore","ironGearItem");
		pipePowerWood = INSTANCE.initItem("BuildCraftTransport","pipePowerWood");
		pipePowerGold = INSTANCE.initItem("BuildCraftTransport","pipePowerGold");
		
		initialized = true;
	}
	
	private Item initItem( String bcClass, String bcField ) {
		try {
			final Class clazz = Class.forName("net.minecraft.src."+bcClass);
			return (Item)clazz.getField(bcField).get(null);
		} catch( Exception e ) {
			System.out.println("Unable to load "+bcField+" from "+bcClass);
			e.printStackTrace();
			throw new RuntimeException("Unable to load required parent module.");
		}
	}
}
