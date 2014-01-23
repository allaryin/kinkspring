package com.allaryin.kinkspring.tile;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class KTileEntity extends TileEntity {
	
	private Map<String,Object> _map = Maps.newHashMap();

	private boolean	_redstone	= false;

	public boolean isRedstonePowered() {
		return _redstone;
	}

	public void pollRedstoneState() {
		final boolean prevRedstone = _redstone;
		_redstone = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord,
				zCoord);
		if( _redstone != prevRedstone ) {
			System.out.println("Redstone -> "+_redstone);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		System.out.println("Writing NBT");
		super.writeToNBT(par1nbtTagCompound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		System.out.println("Reading NBT");
		super.readFromNBT(par1nbtTagCompound);
	}

}
