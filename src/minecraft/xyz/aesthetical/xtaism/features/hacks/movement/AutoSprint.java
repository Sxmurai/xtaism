package xyz.aesthetical.xtaism.features.hacks.movement;

import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;

@Hack(
	name = "Auto Sprint", 
	description = "Makes you sprint whenever you start walking",
	color = 11261429
)
@Keybind(setting = KeybindOpt.KEYBIND_AUTOSPRINT)
@Category(category = Group.MOVEMENT)
public class AutoSprint extends Mod {
	@Override
	public void onUpdate() {
		if (!mc.gameSettings.keyBindForward.isPressed()) {
			return;
		}
		
		mc.player.setSprinting(true);
	}
	
	@Override
	public void onDisable() {
		mc.player.setSprinting(false);
	}
}
