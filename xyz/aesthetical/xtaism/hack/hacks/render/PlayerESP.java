package xyz.aesthetical.xtaism.hack.hacks.render;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;
import xyz.aesthetical.xtaism.helpers.RenderHelper;

public class PlayerESP extends Hack {
	public PlayerESP() {
		super(
			"Player ESP",
			Category.RENDER,
			-1,
			Integer.parseInt("ffc0cb", 16)
		);
	}
	
	@Override
	public void onRender(double partialTick) {
		if (this.isToggled()) {
			if (mc.isSingleplayer()) {
				this.toggle();
				return;
			}
			
			for (EntityPlayer entity : mc.world.playerEntities) {	
				RenderHelper.drawTracers(entity, 128f, 204f, 221f);
			}
		}
	}
}
