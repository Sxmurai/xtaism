package xyz.aesthetical.xtaism.hacks.movement;

import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.hacks.Group;
import xyz.aesthetical.xtaism.hacks.Module;

public class Flight extends Module {
	public float speed = 0.1f;
	
	public Flight() {
		super(
			"Flight", 
			Group.MOVEMENT, 
			Keyboard.KEY_F, 
			Integer.parseInt("3492eb", 16)
		);
	}
	
	public void onDisable() {
		this.minecraft.player.capabilities.isFlying = false;
		super.onDisable();
	}
	
	public void onUpdate() {
		if (this.isEnabled()) {
			this.minecraft.player.capabilities.isFlying = true;
			
			if (this.minecraft.gameSettings.keyBindJump.isPressed()) {
				this.minecraft.player.motionY += 2;
			}
			
			if (this.minecraft.gameSettings.keyBindSneak.isPressed()) {
				this.minecraft.player.motionY -= 2;
			}
			
			if (this.minecraft.gameSettings.keyBindForward.isPressed()) {
				this.minecraft.player.capabilities.setPlayerWalkSpeed(speed);
			}
			
			super.onUpdate();
		} else {
			onDisable();
		}
	}
}
