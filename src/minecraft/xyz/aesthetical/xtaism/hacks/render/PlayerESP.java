package xyz.aesthetical.xtaism.hacks.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.utils.RenderUtils;

@Hack(name = "PlayerESP", description = "Creates tracers to all players", color = 11232833)
@Keybind(key = Keyboard.KEY_3)
@Category(category = Group.RENDER)
public class PlayerESP extends Mod {	
	@Override
	public void onRender() {
		GL11.glPushMatrix();
		
		for (EntityPlayer player : mc.world.playerEntities) {
			float dis = (float) (Minecraft.getMinecraft().player.getDistanceSq(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ) / 20f);
			
			double[] coords = getGlPos(player.posX, player.posY, player.posZ);
			RenderUtils.drawTracers(coords[0], coords[1], coords[2], 2 - dis, dis, 0);
		}
		
		GL11.glPopMatrix();
	}
	
	public double[] getGlPos(double x, double y, double z) {
		return new double[] {
			x - mc.getRenderManager().viewerPosX,
			y - mc.getRenderManager().viewerPosY,
			z - mc.getRenderManager().viewerPosZ
		};
	}
}
