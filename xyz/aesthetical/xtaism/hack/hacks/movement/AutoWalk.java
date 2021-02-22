package xyz.aesthetical.xtaism.hack.hacks.movement;

import net.minecraft.client.settings.KeyBinding;
import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class AutoWalk extends Hack {
	public AutoWalk() {
		super(
			"Auto Walk",
			Category.MOVEMENT,
			-1,
			Integer.parseInt("3377de", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		KeyBinding bind = mc.gameSettings.keyBindForward;
		
		if (this.isToggled()) {			
			bind.setKeyBindState(bind.getKeyCode(), true);
		} else {
			bind.setKeyBindState(bind.getKeyCode(), bind.isKeyDown());	
		}
	}
}
