package com.allaryin.kinkspring.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.allaryin.kinkspring.lib.Config;
import com.allaryin.kinkspring.tile.KTileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KItem extends Item {

	protected int							_iid;		// internal block id
	protected Class<? extends KTileEntity>	tileClass;
	protected Items							enumEntry;

	public KItem(Integer id, Integer iid) {
		super(id);
		_iid = iid;
		setUnlocalizedName("kinkspring.item." + _iid);
		setCreativeTab(CreativeTabs.tabMisc);
	}

	public void setEnumEntry(Items item) {
		enumEntry = item;
	}

	public int getIID() {
		return _iid;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		System.out.println("registering icon for " + enumEntry.name());
		itemIcon = register.registerIcon(Config.TEXTURE_PATH + ":"+ enumEntry.name());
	}
}
