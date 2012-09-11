package kinkspring.blocks;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockCapacitor extends Block {

	public BlockCapacitor(int blockID) {
		super(blockID, 0, Material.iron);
		System.out.println("new blockCapacitor "+blockID);
		setHardness(1.5F);
		setResistance(1F);
		setStepSound(Block.soundMetalFootstep);
		setBlockName("capacitor");
	}
	
	public TileEntity getBlockEntity() {
		return new TileCapacitor();
	}
	
	public boolean blockActivated( World w, int x, int y, int z, EntityPlayer player ) {
		// don't do anything if they shift-click on the block
		if( player.isSneaking() ) {
			return false;
		}
		// TODO: open the GUI for the entity
		return true;
	}
	
	// TODO: detect neighbor block changes
	
	// TODO: texture stuff
}