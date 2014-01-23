package com.allaryin.kinkspring.tile;

import net.minecraft.tileentity.TileEntity;

public class KTileEntity extends TileEntity {
	
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
	
}
