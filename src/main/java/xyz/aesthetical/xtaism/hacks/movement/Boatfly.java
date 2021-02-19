package xyz.aesthetical.xtaism.hacks.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import xyz.aesthetical.xtaism.hacks.Group;
import xyz.aesthetical.xtaism.hacks.Module;

public class Boatfly extends Module {
	public Boatfly() {
		super(
			"Boatfly",
			Group.MOVEMENT,
			Keyboard.KEY_B,
			Integer.parseInt("3492eb", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		if (this.isEnabled()) {
			EntityPlayerSP player = minecraft.player;
			Entity vehicle = player.getRidingEntity();
			
			if (vehicle == null || vehicle.getName() == "Boat") {
				return;
			}
			
			double posY = minecraft.gameSettings.keyBindJump.isKeyDown() ? 0.6 : 0;
			
			vehicle.setSprinting(true);
			vehicle.setVelocity(vehicle.motionX, posY, vehicle.motionZ);
		}
	}
}
