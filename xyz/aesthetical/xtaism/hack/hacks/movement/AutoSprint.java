package xyz.aesthetical.xtaism.hack.hacks.movement;

import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class AutoSprint extends Hack {
	public AutoSprint() {
		super(
			"Auto Sprint",
			Category.MOVEMENT,
			Keyboard.KEY_Z,
			Integer.parseInt("3377de", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if (mc.player.isSneaking()) {
				return;
			}
			
			mc.player.setSprinting(true);
		} else {
			mc.player.setSprinting(mc.player.isSprinting());
		}
	}
}
