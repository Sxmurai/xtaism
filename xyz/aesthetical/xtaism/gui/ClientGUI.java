package xyz.aesthetical.xtaism.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import xyz.aesthetical.xtaism.hack.Hack;

public class ClientGUI extends GuiIngame {
	protected Minecraft mc;
	
	public ClientGUI(Minecraft mcIn) {
		super(mcIn);
		
		this.mc = mcIn;
	}

	public void renderGameOverlay(float partialTicks) {
		super.renderGameOverlay(partialTicks);
		
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        FontRenderer fontrenderer = this.getFontRenderer();
        GlStateManager.enableBlend();
        
    	this.renderHacksOverlay();
        Renderer.renderAndUpdateFrames();
	}
	
	public void renderHacksOverlay() {
		int count = 0;
		
		for (Hack hack : mc.xtaism.getAllHacks()) {
	      	if (hack.isToggled()) {
	      		mc.fontRenderer.drawString(hack.getName(), 2, 2 + (count * 10), hack.getColor());
	      		
        		++count;
        	}
		}
	}
}
