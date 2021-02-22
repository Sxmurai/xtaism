package xyz.aesthetical.xtaism.hack.hacks.movement;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.darkstorm.minecraft.gui.component.basic.BasicSlider;
import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class Spider extends Hack {
	public Spider() {
		super(
			"Spider",
			Category.MOVEMENT,
			-1,
			Integer.parseInt("3377de", 16)
		);
		
		addSetting(new BasicSlider("Velocity", 0.2, 0.1, 0.5, 0.1, ValueDisplay.DECIMAL));
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if (!mc.player.collidedHorizontally) {
				return;
			}
			
			if (mc.player.motionY >= 0.2) {
				return;
			}
			
			double velocity = ((BasicSlider) settings.get(0)).value;
			
			mc.player.setVelocity(mc.player.motionX, velocity, mc.player.motionZ);
		}
	}
}
