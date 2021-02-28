package xyz.aesthetical.xtaism.hacks.player;

import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(name = "Auto Respawn", description = "Automatically respawns you after death", color = 16073282)
@Keybind(key = Keyboard.KEY_NONE)
@Category(category = Group.PLAYER)
public class AutoRespawn extends Mod {
	public void onUpdate() {
		if (mc.player.isDead) {
			mc.player.respawnPlayer();
		}
	}
}
