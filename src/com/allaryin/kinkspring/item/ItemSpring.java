package com.allaryin.kinkspring.item;

import net.minecraft.creativetab.CreativeTabs;

public class ItemSpring extends KItem {

	public ItemSpring(Integer id, Integer iid) {
		super(id,iid);
		setCreativeTab(CreativeTabs.tabMaterials);
		setMaxStackSize(1);
	}

}
