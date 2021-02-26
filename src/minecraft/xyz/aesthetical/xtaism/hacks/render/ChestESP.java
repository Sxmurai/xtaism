package xyz.aesthetical.xtaism.hacks.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.utils.RenderUtils;

@Hack(name = "ChestESP", description = "Creates tracers to all chest entities", color = 11232833)
@Keybind(key = Keyboard.KEY_2)
@Category(category = Group.RENDER)
public class ChestESP extends Mod {
	@Override
	public void onRender() {
		Minecraft mc = Minecraft.getMinecraft();
		
		GL11.glPushMatrix();
		
		for (TileEntity e : mc.world.loadedTileEntityList) {
			if (e instanceof TileEntityChest) {
				//RenderUtils.drawTracers(e., 171, 99, 65);
			}
		}
		
		GL11.glPopMatrix();
	}
}
