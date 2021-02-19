package xyz.aesthetical.xtaism.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import xyz.aesthetical.xtaism.hacks.Module;

public class ClientGUI extends GuiIngame {
	private Minecraft mc;
	
	public ClientGUI(Minecraft mcIn) {
		super(mcIn);
		
		this.mc = mcIn;
	}

    public void renderGameOverlay(float partialTicks)
    {
    	super.renderGameOverlay(partialTicks);
    	
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        FontRenderer fontrenderer = this.getFontRenderer();
        GlStateManager.enableBlend();
        
    	//this.renderCoordinateOverlay(scaledresolution);
    	this.renderHacksEnabledOverlay();
        
        Renderer.renderAndUpdateFrames();
    }
    
    public void renderCoordinateOverlay(ScaledResolution scaledresolution) {
        EntityPlayerSP player = mc.player;
        
        String text = String.format("X: %s, Y: %s, Z: %s", 
        	player.posX,
        	player.posY,
        	player.posZ
        );
        
        int height = scaledresolution.getScaledHeight(); 
        
        mc.fontRenderer.drawString(text, 2, height * 2, Integer.parseInt("ffffff", 16));
    }
    
    public void renderHacksEnabledOverlay() {        
        int count = 0;
        
        for (Module mod : this.mc.xtaism.getModules()) {
        	if (mod.isEnabled()) {
        		this.mc.fontRenderer.drawString(mod.getName(), 2, 2 + (count * 10), mod.getColor());
        		++count;
        	}
        }	
    }
}
