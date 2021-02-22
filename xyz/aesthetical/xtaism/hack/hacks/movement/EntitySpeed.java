package xyz.aesthetical.xtaism.hack.hacks.movement;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.darkstorm.minecraft.gui.component.basic.BasicSlider;

import net.minecraft.entity.Entity;
import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class EntitySpeed extends Hack {
	public EntitySpeed() {
		super(
			"Entity Speed",
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
			
			if (!mc.player.isRiding()) {
				return;
			}
			
			Entity ridingEntity = mc.player.getRidingEntity();
			
			mc.player.getRidingEntity().setVelocity(ridingEntity.motionX * speed, ridingEntity.motionY, ridingEntity.motionZ * speed);
		}
	}
}
