package xyz.aesthetical.xtaism.features.hacks.movement;

import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(name = "Spider", description = "Climbs walls", color = 11261429)
@Keybind(key = Keyboard.KEY_NONE)
@Category(category = Group.MOVEMENT)
public class Spider extends Mod {
	// TODO: make these settings
	private double velocity = 0.3;
	
	@Override
	public void onUpdate() {
		if (mc.player.collidedHorizontally) {
			mc.player.addVelocity(0, velocity, 0);
		}
	}
}
