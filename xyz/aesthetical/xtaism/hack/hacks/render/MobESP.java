package xyz.aesthetical.xtaism.hack.hacks.render;

import xyz.aesthetical.xtaism.helpers.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

import java.awt.Color;

import org.darkstorm.minecraft.gui.util.RenderUtil;
import org.lwjgl.opengl.GL11;

public class MobESP extends Hack {
	public MobESP() {
		super(
			"Mob ESP",
			Category.RENDER,
			-1,
			Integer.parseInt("ffc0cb", 16)
		);
	}
	
	@Override
	public void onRender(double partialTicks) {
		if (this.isToggled()) {
			for (Entity entity : mc.world.loadedEntityList) {
				if (entity instanceof EntityMob) {
					RenderHelper.drawTracersC((EntityMob) entity);
					RenderHelper.drawBoxes((EntityMob) entity);
				}
			}
		}
	}
}
