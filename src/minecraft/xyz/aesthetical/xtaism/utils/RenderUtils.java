package xyz.aesthetical.xtaism.utils;

import java.awt.Color;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;

public class RenderUtils {
	// TODO: fix this shit lmao
	public static void drawTracers(int x, int y, int z, @Nullable Color color) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_LIGHTING);
		//GL11.glDepthMask(false);
		//setupGLOptions();
		
		if (color != null) {
			GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
		}
		
		GL11.glLineWidth(1);

		Vec3d pos = Minecraft.getMinecraft().player.getRenderBoundingBox().getCenter();
		
		GL11.glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex3d(0, Minecraft.getMinecraft().player.posY, 0);
		GL11.glVertex3d(x - pos.x, y - pos.y + Minecraft.getMinecraft().player.height / 20, z - pos.z);
		
		GL11.glEnd();	
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_BLEND);
		//GL11.glDepthMask(true);
		//resetGLOptions();
		GL11.glPopMatrix();
	}
	
//	private static void setupGLOptions() {
//		GL11.glEnable(GL11.GL_BLEND);
//        GL11.glEnable(GL11.GL_LINE_SMOOTH);
//		GL11.glDisable(GL11.GL_LIGHTING);
//		GL11.glDisable(GL11.GL_TEXTURE_2D);
//		GL11.glDisable(GL11.GL_DEPTH_TEST);
//		GL11.glDepthMask(false);
//	}
//	
//	public static void resetGLOptions() {
//		GL11.glDisable(GL11.GL_BLEND);
//		GL11.glEnable(GL11.GL_LIGHTING);
//		GL11.glEnable(GL11.GL_TEXTURE_2D);
//		GL11.glDisable(GL11.GL_LINE_SMOOTH);
//		GL11.glDisable(GL11.GL_BLEND);
//		GL11.glEnable(GL11.GL_DEPTH_TEST);
//		GL11.glDepthMask(true);	
//	}
}
