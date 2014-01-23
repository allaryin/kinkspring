package com.allaryin.kinkspring.tile;

import net.minecraft.nbt.NBTTagCompound;

public class TileCapacitor extends KTileEntity {
	private int facing = 0;
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("facing", (byte) facing);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		facing = nbt.getByte("facing");
	}

}
