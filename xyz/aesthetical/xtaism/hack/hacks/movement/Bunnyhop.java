package xyz.aesthetical.xtaism.hack.hacks.movement;

import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class Bunnyhop extends Hack {
	public Bunnyhop() {
		super(
			"Bunnyhop",
			Category.MOVEMENT,
			-1,
			Integer.parseInt("3377de", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {			
			if (mc.player.isSneaking() || !mc.player.onGround) {
				return;
			}
						
			if (mc.player.isSprinting()) {
				mc.player.jump();	
			}
		}
	}
}
