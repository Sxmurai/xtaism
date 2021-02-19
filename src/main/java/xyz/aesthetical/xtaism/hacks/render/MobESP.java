package xyz.aesthetical.xtaism.hacks.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import xyz.aesthetical.xtaism.hacks.Group;
import xyz.aesthetical.xtaism.hacks.Module;
import xyz.aesthetical.xtaism.utils.RenderUtils;
import xyz.aesthetical.xtaism.utils.RenderUtils.ESPMode;

public class MobESP extends Module {
	public MobESP() {
		super(
			"MobESP",
			Group.RENDER, 
			-1,
			Integer.parseInt("ffc0cb", 16)
		);
	}
	
	@Override
	public void onRender() {
		if (this.isEnabled()) {
			for (Entity entity : minecraft.world.loadedEntityList) {
				if (entity instanceof EntityMob) {
					RenderUtils.drawESPBox(entity, ESPMode.ENEMY);
				}
			}
		}
	}
}
