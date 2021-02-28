package xyz.aesthetical.xtaism.features.hacks.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.utils.RenderUtils;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;

@Hack(name = "ChestESP", description = "Creates tracers to all chest entities", color = 11232833)
@Keybind(setting = KeybindOpt.KEYBIND_CHESTESP)
@Category(category = Group.RENDER)
public class ChestESP extends Mod {
	@Override
	public void onRender() {		
		GL11.glPushMatrix();
		
		for (TileEntity e : mc.world.loadedTileEntityList) {
			if (e instanceof TileEntityChest) {
				BlockPos pos = e.getPos();
				
				RenderUtils.drawTracers(pos.getX(), pos.getY(), pos.getZ(), 171, 99, 65);
			}
		}
		
		GL11.glPopMatrix();
	}
}
