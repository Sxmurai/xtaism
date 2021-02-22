package xyz.aesthetical.xtaism.hack.hacks.player;

import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class AutoRespawn extends Hack {
	public AutoRespawn() {
		super(
			"Auto Respawn",
			Category.PLAYER,
			-1,
			Integer.parseInt("e3d022", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if (mc.player.isDead) {
				mc.player.respawnPlayer();
			}
		}
	}
}
