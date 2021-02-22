package xyz.aesthetical.xtaism.hack.hacks.render;

import java.awt.Color;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.math.BlockPos;
import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;
import xyz.aesthetical.xtaism.helpers.RenderHelper;

public class ChestESP extends Hack {
	public ChestESP() {
		super(
			"Chest ESP",
			Category.RENDER,
			-1,
			Integer.parseInt("ffc0cb", 16)
		);
	}
	
	@Override
	public void onRender(double partialTick) {
		if (this.isToggled()) {
			for (TileEntity entity : mc.world.loadedTileEntityList) {
				if (
					entity instanceof TileEntityChest ||
					entity instanceof TileEntityShulkerBox ||
					entity instanceof TileEntityEnderChest
				) {
					BlockPos pos = entity.getPos();
					
					RenderHelper.drawTracers(
						pos.getX(), 
						pos.getY(), 
						pos.getZ(), 
						mc.player.height, 
						252f, 111f, 3f,
						false
					);
				}
			}
		}
	}
}
