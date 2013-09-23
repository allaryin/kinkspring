package com.allaryin.kinkspring.block;

import java.util.HashMap;
import java.util.Map;

import com.allaryin.kinkspring.lib.Localization;

public enum Blocks {
	springCapacitor(0);

	public KBlock	block;
	public int		id;	// minecraft block id
	public int		bid;	// internal block id
	
	private Blocks(int bid) {
		this.bid = bid;
	}

	private static Map<Integer, Blocks>	_bidMap	= new HashMap<Integer, Blocks>();

	public void setBlock(KBlock block) {
		this.block = block;
		_bidMap.put(bid, this);
		block.setUnlocalizedName(Localization.NS + this.name());
	}

	public static Blocks getBlockByBID(int bid) {
		return _bidMap.get(bid);
	}
}
