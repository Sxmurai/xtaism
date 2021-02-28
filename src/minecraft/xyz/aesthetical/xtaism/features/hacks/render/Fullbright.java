package xyz.aesthetical.xtaism.features.hacks.render;

import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;

@Hack(name = "Fullbright", description = "Makes it so you can see even in the dark", color = 16574467)
@Keybind(setting = KeybindOpt.KEYBIND_FULLBRIGHT)
@Category(category = Group.RENDER)
public class Fullbright extends Mod {	
	@Override
	public void onEnable() {
		mc.gameSettings.gammaSetting = 100;
	}
	
	@Override
	public void onDisable() {
		mc.gameSettings.gammaSetting = 0;
	}
}
