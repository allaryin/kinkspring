package com.allaryin.kinkspring.item;

import java.util.HashMap;
import java.util.Map;

import com.allaryin.kinkspring.lib.Localization;

public enum Items {
	spring(0);

	public KItem	item;
	public int		id;		// minecraft item id
	public int		iid;	// internal item id
	
	private Items(int iid) {
		this.iid = iid;
	}

	private static Map<Integer, Items>	_iidMap	= new HashMap<Integer, Items>();

	public void setItem(KItem item) {
		this.item = item;
		_iidMap.put(iid, this);
		item.setUnlocalizedName(Localization.NS + this.name());
	}

	public static Items getItemByIID(int iid) {
		return _iidMap.get(iid);
	}
}
