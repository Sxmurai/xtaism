package xyz.aesthetical.xtaism.helpers;

import java.awt.Color;

import javax.annotation.Nullable;

import org.darkstorm.minecraft.gui.util.RenderUtil;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityBannerRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

public class RenderHelper {
	public static BlockPos getCameraBlockPosition() {
		return TileEntityRendererDispatcher.instance.cameraHitResult.getBlockPos();
	}
	
	public static BlockPos getPlayerPosition() {
		return Minecraft.getMinecraft().player.getPosition();
	}
	
	private static void setup3DModel(boolean reset) {
		if (reset) {
			GL11.glDisable(3042);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LINE_SMOOTH);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(2929);
			GL11.glDepthMask(true);
		} else {
			GL11.glEnable(3042);
			GL11.glEnable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	        GL11.glEnable(GL11.GL_LINE_SMOOTH);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(2929);
			GL11.glDepthMask(false);	
		}
	}
	
	public static void drawTracers(EntityMob entity, float r, float g, float b) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;

		drawTracers(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ, player.height, r, g, b, false);
	}
	
	public static void drawTracers(EntityPlayer entity, float r, float g, float b) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;

		drawTracers(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ, player.height, r, g, b, false);
	}
	
	
	public static void drawTracersC(EntityPlayer entity) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;

		drawTracers(
			entity.lastTickPosX, 
			entity.lastTickPosY, 
			entity.lastTickPosZ, 
			player.height, 
			0f, 0f, 0f,
			true
		);
	}
	
	public static void drawTracersC(EntityMob entity) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;

		drawTracers(
			entity.lastTickPosX, 
			entity.lastTickPosY, 
			entity.lastTickPosZ, 
			player.height, 
			0f, 0f, 0f,
			true
		);
	}
	
	public static void drawTracers(
		double lX, 
		double lY, 
		double lZ, 
		float height,
		float r, float g, float b,
		boolean useColorfulESP
	) {
		GL11.glPushMatrix();
		setup3DModel(false);
		
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		
		if (r != 0 && !useColorfulESP) {
			GL11.glColor4f(r, g, b, 1f);
		}
		
		if (useColorfulESP) {
			float dis = (float) (Minecraft.getMinecraft().player.getDistanceSq(lX, lY, lZ) / 20f);
			
			GL11.glColor4f(2 - dis, dis, 0, .5f);	
		}
		
		GL11.glLineWidth(1);
		
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		
		Vec3d e = player.getRenderBoundingBox().getCenter();
		
		GL11.glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex3d(0, 0, 0);
		GL11.glVertex3d(
			lX - e.x,
			lY - e.y + player.height / 2,
			lZ- e.z
		);
		
		GL11.glEnd();
		setup3DModel(true);
		GL11.glPopMatrix();
	}
	
	public static void drawBoxes(EntityMob entity) {
		GL11.glPushMatrix();
		setup3DModel(false);
		
		
		
		setup3DModel(true);
		GL11.glPopMatrix();
	}
}
