package xyz.aesthetical.xtaism.hacks.render;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityShulkerBox;
import xyz.aesthetical.xtaism.hacks.Group;
import xyz.aesthetical.xtaism.hacks.Module;
import xyz.aesthetical.xtaism.utils.RenderUtils;
import xyz.aesthetical.xtaism.utils.RenderUtils.ESPMode;

public class ChestESP extends Module {
	public ChestESP() {
		super(
			"ChestESP",
			Group.RENDER, 
			-1,
			Integer.parseInt("ffc0cb", 16)
		);
	}
	
	@Override
	public void onRender() {
		if (this.isEnabled()) {
			for (TileEntity entity : minecraft.world.loadedTileEntityList) {				
				if (
					entity instanceof TileEntityChest ||
					entity instanceof TileEntityShulkerBox ||
					entity instanceof TileEntityEnderChest
				) {
					RenderUtils.drawESPTBox(entity, ESPMode.CHEST);
				}
			}
		}
	}
}
