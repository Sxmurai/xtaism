package xyz.aesthetical.xtaism.features.gui.overlay;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.features.gui.clickgui.Renderer;

public class XtaismGUI extends GuiIngame {
	protected Minecraft mc;
	
	public XtaismGUI(Minecraft mcIn) {
		super(mcIn);
		
		mc = mcIn;
	}
	
	public void renderGameOverlay(float partialTicks) {
		super.renderGameOverlay(partialTicks);
		
		ScaledResolution scaledRes = new ScaledResolution(this.mc);
		FontRenderer fontRenderer = this.getFontRenderer();
		GlStateManager.enableBlend();
		
		// add the things to render
		this.renderHacksOverlay(fontRenderer, scaledRes.getScaledWidth());
		Renderer.renderAndUpdateFrames();
		
		if (mc.xtaism.settings.shouldShowCoords()) {
			this.renderCoordinateOverlay(fontRenderer, scaledRes.getScaledWidth(), scaledRes.getScaledHeight());
		}
		
		if (mc.xtaism.settings.shouldShowFPS()) {
			this.renderFPSOverlay(fontRenderer, scaledRes.getScaledWidth(), scaledRes.getScaledHeight());
		}
	}
	
	private void renderHacksOverlay(FontRenderer renderer, int displayWidth) {
		int posY = 0;
						
		for (Mod hack : this.mc.xtaism.getHackManager().getHacks()) {
			if (hack.isToggled()) {
				// calculate where the text should be, since we're putting it on the right
				int posX = displayWidth - mc.fontRenderer.getStringWidth(hack.getName()) - 2;
				
				renderer.drawString(hack.getName(), posX, 2 + (posY * 10), hack.getColor());
				++posY;
			}
		}
	}
	
	private void renderCoordinateOverlay(FontRenderer renderer, int displayWidth, int displayHeight) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		boolean inNether = player.dimension == -1;
				
		String text = getOverworldCoords(inNether);
		
		int posX = displayWidth - renderer.getStringWidth(text) - 5;
		int color = getColorBasedOnTime();
		
		renderer.drawString(
			text, 
			posX, displayHeight - 10, 
			color
		);
		
		if (inNether) {
			String textN = String.format("X: %s, Y: %S, Z: %s", f(player.posX), f(player.posY), f(player.posZ));
			
			int posXN = displayWidth - renderer.getStringWidth(textN) - 5;
						
			renderer.drawString(
				textN, 
				posXN, displayHeight - (renderer.FONT_HEIGHT * 2) - 5, 
				color
			);	
		}
	}
	
	public String getOverworldCoords(boolean inNether) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;

		List<String> coords = new LinkedList();
		coords.add(f(player.posX));
		coords.add(f(player.posY));
		coords.add(f(player.posZ));
		
		if (inNether) {
			for (String coord : coords) {
				// if its the y value, we dont want to multiply by 8
				if (coords.indexOf(coord) == 1) {
					continue;
				}
				
				coords.set(coords.indexOf(coord), String.valueOf(Integer.parseInt(coord) * 8));
			}
		}
		
		return String.format("%sX: %s, Y: %s, Z: %s", inNether ? "Overworld - " : "", coords.get(0), coords.get(1), coords.get(2));
	}
	
	private void renderFPSOverlay(FontRenderer renderer, int displayWidth, int displayHeight) {
		boolean inNether = Minecraft.getMinecraft().player.dimension == -1;
		
		String text = "FPS: " + String.valueOf(mc.getDebugFPS());
		
		int posX = displayWidth - renderer.getStringWidth(text) - 5;
		int color = getColorBasedOnTime();
		
		renderer.drawString(
			text, 
			posX, 
			mc.xtaism.settings.shouldShowCoords() 
				? displayHeight - (renderer.FONT_HEIGHT * (inNether ? 3 : 2)) - 5 
				: displayHeight - 10, 
			color
		);
	}
	
	private String f(double num) {
		NumberFormat format = NumberFormat.getInstance();
		format.setRoundingMode(RoundingMode.DOWN);
		format.setMaximumFractionDigits(0);
		
		return format.format(num);
	}
	
	private int getColorBasedOnTime() {
		if (Minecraft.getMinecraft().player.dimension == -1) return 16777215;
		return Minecraft.getMinecraft().world.getWorldTime() < 13000 ? 0 : 16777215;
	}
}
