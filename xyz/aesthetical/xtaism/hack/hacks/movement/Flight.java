package xyz.aesthetical.xtaism.hack.hacks.movement;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.darkstorm.minecraft.gui.component.basic.BasicCheckButton;
import org.darkstorm.minecraft.gui.component.basic.BasicSlider;

import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class Flight extends Hack {
	public Flight() {
		super(
			"Flight",
			Category.MOVEMENT,
			-1,
			Integer.parseInt("3377de", 16)
		);
		
		addSetting(new BasicSlider("Speed", 0.2, 0.1, 5, 0.1, ValueDisplay.DECIMAL));
		addSetting(new BasicCheckButton("Use obvious flying"));
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			boolean useObviousFlying = ((BasicCheckButton) settings.get(1)).isSelected();
			
			if (mc.gameSettings.keyBindJump.isPressed()) {
				mc.player.motionY += useObviousFlying ? 2 : .4;
			}
			
			if (mc.gameSettings.keyBindSneak.isPressed()) {
				mc.player.motionY -= useObviousFlying ? 2 : .4;
			}
						
			if (useObviousFlying) {
				mc.player.capabilities.allowFlying = true;
			} else {
				if (!mc.player.isCreative()) {
					mc.player.capabilities.allowFlying = false;
				}
			}
			
			if (mc.gameSettings.keyBindForward.isPressed()) {
				mc.player.capabilities.setFlySpeed((float) ((BasicSlider) settings.get(0)).value);
			}
		}
	}
}
