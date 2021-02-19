package xyz.aesthetical.xtaism.hacks.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import xyz.aesthetical.xtaism.hacks.Group;
import xyz.aesthetical.xtaism.hacks.Module;

public class Jesus extends Module {
	public Jesus() {
		super(
			"Jesus",
			Group.MOVEMENT,
			Keyboard.KEY_J,
			Integer.parseInt("3492eb", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		if (this.isEnabled()) {
			EntityPlayerSP player = minecraft.player;
			
			if (player.isSneaking()) {
				return;
			}
			
			if (player.isInWater()) {
				minecraft.player.setVelocity(player.motionX, 0.12, player.motionZ);				
			}
			
			super.onUpdate();
		}
	}
}
