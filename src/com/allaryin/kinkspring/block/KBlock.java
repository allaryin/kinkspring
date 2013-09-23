package com.allaryin.kinkspring.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.allaryin.kinkspring.tile.KTileEntity;

public class KBlock extends BlockContainer {

	protected int							_bid;		// internal block id
	protected Class<? extends KTileEntity>	tileClass;

	public KBlock(int id, int bid, Material mat) {
		super(id, mat);
		_bid = bid;
		setUnlocalizedName("kinkspring.block." + _bid);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	public int getBID() {
		return _bid;
	}

	public void setTileClass(Class<? extends KTileEntity> tileClass) {
		this.tileClass = tileClass;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}

}
