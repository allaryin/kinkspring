package kinkspring.blocks;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.buildcraft.api.APIProxy;

public class BlockCapacitor extends BlockContainer {

	public BlockCapacitor(int blockID) {
		super(blockID, 0, Material.iron);
		System.out.println("new blockCapacitor "+blockID);
		setHardness(1.5F);
		setResistance(1F);
		setStepSound(Block.soundMetalFootstep);
		setBlockName("kinkspring.capacitor");
	}
	
	public TileEntity getBlockEntity() {
		return new TileCapacitor();
	}
	public TileEntity getBlockEntity(int meta) {
		return getBlockEntity();
	}
	
	public boolean blockActivated( World w, int x, int y, int z, EntityPlayer player ) {
		// don't do anything if they shift-click on the block
		if( player.isSneaking() ) {
			return false;
		}
		// TODO: open the GUI for the entity
		TileCapacitor tile = (TileCapacitor) w.getBlockTileEntity(x, y, z);
		
		if ( tile != null ) {
			if ( !APIProxy.isClient(tile.worldObj) )
				ModLoader.openGUI(player,new BlockCapacitorGui(player, tile));
			return true;
		}
		return true;
	}
	
	// TODO: detect neighbor block changes
	
	// If this function is not implemented, it uses the
	// constructor values.
	@Override
	public int getBlockTextureFromSide(int side) {
		// We want the texture next to our default texture from this block for the bottom and top side
		// so we just add 1 when the side is 0 or 1 else we return the default one
		switch(side){
		case 0: // -Y (bottom side)
			return this.blockIndexInTexture + 3;
		case 1: // +Y (top side)
			return this.blockIndexInTexture + 2;
		}
		return this.blockIndexInTexture;
	}

	@Override
	public String getTextureFile(){
		return "/textures/blocks.png"; //return the block texture where the block texture is saved in
	}
}
