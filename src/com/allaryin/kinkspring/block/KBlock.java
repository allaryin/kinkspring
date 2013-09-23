package com.allaryin.kinkspring.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.allaryin.kinkspring.lib.Config;
import com.allaryin.kinkspring.tile.KTileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KBlock extends BlockContainer {

	protected int							_bid;		// internal block id
	protected Class<? extends KTileEntity>	tileClass;
	protected Blocks						enumEntry;

	public KBlock(Integer id, Integer bid, Material mat) {
		super(id, mat);
		_bid = bid;
		setUnlocalizedName("kinkspring.block." + _bid);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	public void setEnumEntry(Blocks block) {
		enumEntry = block;
	}

	public int getBID() {
		return _bid;
	}

	public void setTileClass(Class<? extends KTileEntity> tileClass) {
		this.tileClass = tileClass;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			int neighborID) {
		KTileEntity tile = (KTileEntity) world.getBlockTileEntity(x, y, z);
		if (tile != null) {
			tile.pollRedstoneState();
		}
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return createNewTileEntity(world);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		try {
			return tileClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return new KTileEntity();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		System.out.println("registering icon for " + enumEntry.name());
		blockIcon = register.registerIcon(Config.TEXTURE_PATH + ":"
				+ enumEntry.name());
	}
}
