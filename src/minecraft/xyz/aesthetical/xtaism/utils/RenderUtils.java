package xyz.aesthetical.xtaism.utils;

import java.awt.Color;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.Vec3d;

public class RenderUtils {
	public static void drawTracers(double x, double y, double z, float r, float g, float b) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glColor4f(r, g, b, 1);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glLineWidth(2);
		
		EntityPlayerSP player = Minecraft.getMinecraft().player;
        //Vec3d eyes = player.getLook(Minecraft.getMinecraft().getRenderPartialTicks());
		
		GL11.glLoadIdentity();
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex3d(-0.001, 0, -1);
		GL11.glVertex3d(x, y, z);
		GL11.glVertex3d(x, y, z);
		
		GL11.glEnd();	
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		GL11.glColor3f(1, 1, 1);
	}
	
	public static void drawTextBoxAboveEntity() {
		
	}
}
