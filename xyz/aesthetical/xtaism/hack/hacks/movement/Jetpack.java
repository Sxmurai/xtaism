package xyz.aesthetical.xtaism.hack.hacks.movement;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;
import org.darkstorm.minecraft.gui.component.basic.BasicCheckButton;
import org.darkstorm.minecraft.gui.component.basic.BasicSlider;

import net.minecraft.network.play.client.CPacketPlayer;
import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class Jetpack extends Hack {
	public Jetpack() {
		super(
			"Jetpack",
			Category.MOVEMENT,
			-1,
			Integer.parseInt("3377de", 16)
		);
		
		addSetting(new BasicSlider("Velocity", 0.2, 0.1, 0.5, 0.1, ValueDisplay.DECIMAL));
		addSetting(new BasicCheckButton("Prevent fall damage"));
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			double velocity = ((BasicSlider) settings.get(0)).value;
			boolean preventFallDamage = ((BasicCheckButton) settings.get(1)).isSelected();
			
			if (mc.gameSettings.keyBindJump.isKeyDown()) {
				mc.player.setVelocity(mc.player.motionX, velocity, mc.player.motionZ);				
			}
			
			if (mc.player.fallDistance > 2f && !mc.xtaism.isHackEnabled("No Fall") && preventFallDamage) {
				mc.player.connection.sendPacket(new CPacketPlayer(true));
			}
		}
	}
}
