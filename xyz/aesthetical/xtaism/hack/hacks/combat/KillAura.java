package xyz.aesthetical.xtaism.hack.hacks.combat;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHand;
import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class KillAura extends Hack {
	public KillAura() {
		super(
			"Kill Aura",
			Category.COMBAT,
			-1,
			Integer.parseInt("e32222", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if (mc.player.getSwingProgress(0) != 0) {
				return;
			}
						
			double range = Math.pow(4.25, 2);
			
			for (Entity entity : mc.world.loadedEntityList) {
				if (mc.player.getDistanceSq(entity.getPosition()) <= range) {
					return;
				}
				
				if (entity.canBeAttackedWithItem()) {
					mc.player.swingArm(EnumHand.MAIN_HAND);
				}
			}
		}
	}
}
