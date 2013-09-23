package com.allaryin.kinkspring.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.allaryin.kinkspring.tile.KTileEntity;

public class KItem extends Item {

	protected int							_iid;		// internal block id
	protected Class<? extends KTileEntity>	tileClass;

	public KItem(Integer id, Integer iid) {
		super(id);
		_iid = iid;
		setUnlocalizedName("kinkspring.item." + _iid);
		setCreativeTab(CreativeTabs.tabMisc);
	}

	public int getIID() {
		return _iid;
	}

}
