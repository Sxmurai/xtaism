package xyz.aesthetical.xtaism.features.gui.notifications;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Notification {
	private boolean isVisible = false;
	private String text;
	private int time = 5000;
	
	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public void draw(ScaledResolution res) {		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		
		int textWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(this.text);
		
		GL11.glColor3d(169, 169, 169);
		GL11.glRectd((res.getScaledWidth() - 4) - textWidth, res.getScaledHeight() - 400, res.getScaledWidth() - 2, res.getScaledHeight() - 100);
		
		Minecraft.getMinecraft().fontRenderer.drawString(text, (res.getScaledWidth() - 6) - textWidth, res.getScaledHeight() - 150, 16777215);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}
}
