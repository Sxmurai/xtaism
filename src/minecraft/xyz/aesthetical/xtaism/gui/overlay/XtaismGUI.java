package xyz.aesthetical.xtaism.gui.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import xyz.aesthetical.xtaism.entities.hacks.Mod;

public class XtaismGUI extends GuiIngame {
	protected Minecraft mc;
	
	public XtaismGUI(Minecraft mcIn) {
		super(mcIn);
		
		mc = mcIn;
	}
	
	public void renderGameOverlay(float partialTicks) {
		super.renderGameOverlay(partialTicks);
		
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        FontRenderer fontrenderer = this.getFontRenderer();
        GlStateManager.enableBlend();
        
    	this.renderHacksOverlay(fontrenderer, scaledresolution.getScaledWidth());
	}
	
	private void renderHacksOverlay(FontRenderer renderer, int displayWidth) {
		int posY = 0;
						
		for (Mod hack : this.mc.xtaism.getHacks()) {
			if (hack.isToggled()) {
				// calculate where the text should be, since we're putting it on the right
				int posX = displayWidth - mc.fontRenderer.getStringWidth(hack.getName()) - 5;
				
				renderer.drawString(hack.getName(), posX, 2 + (posY * 10), hack.getColor());
				++posY;
			}
		}
	}
}
