package xyz.aesthetical.xtaism.utils;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderUtils {
	private static Minecraft mc = Minecraft.getMinecraft();
	
	public static void drawESPTBox(TileEntity entity, ESPMode espMode) {
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);

		float r = 0;
		float g = 0;
		float b = 0;
		float a = 1;
		
		switch (espMode) {
			case ENEMY: {
				// Red
				r = 231;
				g = 16;
				b = 16;
				a = 0.9803921568627451f;
				
				break;
			}
			
			case PLAYER: {
				// Green
				r = 36;
				g = 214;
				b = 30;
				
				break;
			}
			
			case CHEST: {
				// Orange
				r = 215;
				g = 78;
				b = 30;
				
				break;
			}
			
			case PORTAL: {
				// The portal purple, obviously
				r = 151;
				g = 30;
				b = 214;
				
				break;
			}
		}
				
		RenderManager mcRenderer = mc.getRenderManager();
		
		RenderGlobal.renderFilledBox(
			new AxisAlignedBB(
				entity.getPos().getX() 
					- 0.05 
					- entity.getPos().getX() 
					+ (entity.getPos().getX() - mcRenderer.viewerPosX),
				entity.getPos().getY()
					- entity.getPos().getY()
					+ (entity.getPos().getY() - mcRenderer.viewerPosY),
				entity.getPos().getZ()
					- 0.05 
					- entity.getPos().getZ()
					+ (entity.getPos().getZ() - mcRenderer.viewerPosZ),
				(entity.getPos().getX() + 1.0)
					- 0.05 
					- entity.getPos().getX()
					+ (entity.getPos().getX() - mcRenderer.viewerPosX),	
				(entity.getPos().getY() + 1.0)
					+ 0.1
					- entity.getPos().getY()
					+ (entity.getPos().getY() - mcRenderer.viewerPosY),
				(entity.getPos().getZ() + 1.0)
					- 0.05 
					- entity.getPos().getZ()
					+ (entity.getPos().getZ() - mcRenderer.viewerPosZ)
			),
			r, g, b, a
		);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnd();
	}
	
	public static void drawESPBox(Entity entity, ESPMode espMode) {
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);

		float r = 0;
		float g = 0;
		float b = 0;
		float a = 1;
		
		switch (espMode) {
			case ENEMY: {
				// Red
				r = 231;
				g = 16;
				b = 16;
				a = 0.9803921568627451f;
				
				break;
			}
			
			case PLAYER: {
				// Green
				r = 36;
				g = 214;
				b = 30;
				
				break;
			}
			
			case CHEST: {
				// Orange
				r = 215;
				g = 78;
				b = 30;
				
				break;
			}
			
			case PORTAL: {
				// The portal purple, obviously
				r = 151;
				g = 30;
				b = 214;
				
				break;
			}
		}
				
		RenderManager mcRenderer = mc.getRenderManager();
		
		RenderGlobal.renderFilledBox(
			new AxisAlignedBB(
				entity.getEntityBoundingBox().minX 
					- 0.05 
					- entity.posX 
					+ (entity.posX - mcRenderer.viewerPosX),
				entity.getEntityBoundingBox().minY
					- entity.posY
					+ (entity.posY - mcRenderer.viewerPosY),
				entity.getEntityBoundingBox().minZ
					- 0.05 
					- entity.posZ
					+ (entity.posZ - mcRenderer.viewerPosZ),
				entity.getEntityBoundingBox().maxX 
					- 0.05 
					- entity.posX 
					+ (entity.posX - mcRenderer.viewerPosX),	
				entity.getEntityBoundingBox().maxY
					+ 0.1
					- entity.posY
					+ (entity.posY - mcRenderer.viewerPosY),
				entity.getEntityBoundingBox().maxZ
					- 0.05 
					- entity.posZ
					+ (entity.posZ - mcRenderer.viewerPosZ)
			),
			r, g, b, a
		);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnd();
	}
	
	public enum ESPMode {
		ENEMY,
		PLAYER,
		CHEST,
		PORTAL,
	}
}
