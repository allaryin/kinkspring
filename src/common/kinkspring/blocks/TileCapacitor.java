package kinkspring.blocks;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.buildcraft.api.IPowerReceptor;
import net.minecraft.src.buildcraft.api.PowerFramework;
import net.minecraft.src.buildcraft.api.PowerProvider;

public class TileCapacitor extends TileEntity implements IPowerReceptor {

	// TODO: figure this thing out :)
	
	private PowerProvider powerProvider;
	
	public TileCapacitor() {
		System.out.println( "new TileCapacitor..." );
		this.powerProvider = PowerFramework.currentFramework.createPowerProvider();
		this.powerProvider.configure(2000, 1, 5, 10, 50000);	// random values
	}
	
	@Override
	public void updateEntity() {
		if( this.worldObj.getWorldTime() % 40l == 0l ) {
			System.out.println("updateEntity - "+powerProvider.energyStored);
		}
		super.updateEntity();
	}
	
	@Override
	public void setPowerProvider(PowerProvider provider) {
		this.powerProvider = provider;
	}

	@Override
	public PowerProvider getPowerProvider() {
		return this.powerProvider;
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		powerProvider.energyStored = tag.getInteger("power");
	}
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("power", powerProvider.energyStored);
	}

	@Override
	public void doWork() {
		System.out.println("doWork");
	}

	@Override
	public int powerRequest() {
		return getPowerProvider().maxEnergyReceived;
	}

}
