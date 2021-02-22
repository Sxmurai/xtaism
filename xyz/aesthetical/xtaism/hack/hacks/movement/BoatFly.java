package xyz.aesthetical.xtaism.hack.hacks.movement;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.darkstorm.minecraft.gui.component.basic.BasicSlider;

import net.minecraft.entity.Entity;
import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class BoatFly extends Hack {
	public BoatFly() {
		super(
			"Boatfly",
			Category.MOVEMENT,
			-1,
			Integer.parseInt("3377de", 16)
		);
		
		addSetting(new BasicSlider("Velocity", 0.6, 0.1, 0.9, 0.1, ValueDisplay.DECIMAL));
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			Entity ridingEntity = mc.player.getRidingEntity();
			if (ridingEntity == null) {
				return;
			}
			
			double velocity = ((BasicSlider) settings.get(0)).value;
			
			double y = mc.gameSettings.keyBindJump.isKeyDown() ? velocity : 0;
			
			ridingEntity.setSprinting(true);
			ridingEntity.setVelocity(ridingEntity.motionX, y, ridingEntity.motionZ);
		}
	}
}
