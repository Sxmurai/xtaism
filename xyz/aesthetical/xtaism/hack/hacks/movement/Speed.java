package xyz.aesthetical.xtaism.hack.hacks.movement;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.darkstorm.minecraft.gui.component.basic.BasicSlider;

import net.minecraft.util.math.Vec3d;
import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class Speed extends Hack {
	public Speed() {
		super(
			"Speed",
			Category.MOVEMENT,
			-1,
			Integer.parseInt("3377de", 16)
		);
		
		addSetting(new BasicSlider("Speed", 1.65, 1.0, 2, 0.1, ValueDisplay.DECIMAL));
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			double speed = ((BasicSlider) settings.get(0)).value;
			
			if (!mc.player.onGround || mc.player.isSneaking()) {
				return;
			}
			
			mc.player.setVelocity(mc.player.motionX * speed, mc.player.motionY, mc.player.motionZ * speed);
		}
	}
}
