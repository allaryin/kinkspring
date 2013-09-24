package com.allaryin.kinkspring.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpring extends KItem {

	public ItemSpring(Integer id, Integer iid) {
		super(id,iid);
		setCreativeTab(CreativeTabs.tabMaterials);
		setMaxStackSize(1);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List info,
			boolean useExtraInfo) {
		info.add("An unwound spring.");
	}
}
