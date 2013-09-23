package com.allaryin.kinkspring.block;

import net.minecraft.block.material.Material;

public class BlockCapacitor extends KBlock {

	public BlockCapacitor(int id, int bid) {
		super(id, bid, Material.iron);
		setHardness(0.5F);
		setStepSound(soundMetalFootstep);
	}

}
