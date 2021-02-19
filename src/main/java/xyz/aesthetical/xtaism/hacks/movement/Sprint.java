package xyz.aesthetical.xtaism.hacks.movement;

import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.hacks.Group;
import xyz.aesthetical.xtaism.hacks.Module;

public class Sprint extends Module {
	public Sprint() {
		super(
			"Sprint", 
			Group.MOVEMENT, 
			Keyboard.KEY_Z, 			
			Integer.parseInt("3492eb", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		if (this.isEnabled()) {
			minecraft.player.setSprinting(true);
		} else {
			minecraft.player.setSprinting(false);
		}
	}
}
