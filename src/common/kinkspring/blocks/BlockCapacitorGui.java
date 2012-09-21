package kinkspring.blocks;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Gui;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

public class BlockCapacitorGui extends GuiScreen {
	private TileCapacitor tileEntity;
	
	public BlockCapacitorGui(EntityPlayer player, TileCapacitor tile)
	{
		tileEntity = tile;
	}
	
	private int xSizeOfTexture = 176, ySizeOfTexture = 88;
	
	public int xLeft() { return (this.width - xSizeOfTexture) / 2; }
	public int yTop() { return (this.height - ySizeOfTexture) / 2; }

	@Override
	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();

        int tex_handle = this.mc.renderEngine.getTexture("/textures/gui_background.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(tex_handle);
        
        int stored_energy = tileEntity.getPowerProvider().energyStored;
        int max_energy = tileEntity.getPowerProvider().maxEnergyStored;
        
        drawTexturedModalRect(xLeft(), yTop(), 0, 0, xSizeOfTexture, ySizeOfTexture);
        drawString( fontRenderer, stored_energy + "/" + max_energy, xLeft()+20, yTop()+20, 0xffffff );
        
		super.drawScreen(x, y, f);
	}

	public void initGui()
	{
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, xLeft() + xSizeOfTexture - 105, yTop() + ySizeOfTexture - 25, 100, 20, "OK"));
	}

	@Override
	public boolean doesGuiPauseGame()
	{
	         return false;
	}
}
