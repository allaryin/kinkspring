package kinkspring.blocks;

import net.minecraft.src.TileEntity;
import net.minecraft.src.buildcraft.api.IPowerReceptor;
import net.minecraft.src.buildcraft.api.PowerFramework;
import net.minecraft.src.buildcraft.api.PowerProvider;

public class TileCapacitor extends TileEntity implements IPowerReceptor {

	// TODO: figure this thing out :)
	
	private PowerProvider powerProvider;
	
	public TileCapacitor() {
		this.powerProvider = PowerFramework.currentFramework.createPowerProvider();
		this.powerProvider.configure(2000, 1, 5, 10, 50000);	// random values
	}
	
	@Override
	public void setPowerProvider(PowerProvider provider) {
		this.powerProvider = provider;
	}

	@Override
	public PowerProvider getPowerProvider() {
		return this.powerProvider;
	}

	@Override
	public void doWork() {
		// TODO Auto-generated method stub
	}

	@Override
	public int powerRequest() {
		return getPowerProvider().maxEnergyReceived;
	}

}
