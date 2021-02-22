package xyz.aesthetical.xtaism.hacks.movement;

import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(name = "Bunnyhop", description = "Makes you jump while running", color = 11261429)
@Keybind(key = Keyboard.KEY_NONE)
@Category(category = Group.MOVEMENT)
public class BunnyHop extends Mod {
	@Override
	public void onUpdate() {
		if (!mc.player.isSprinting() || !mc.player.onGround) {
			return;
		}
		
		mc.player.jump();
	}
}