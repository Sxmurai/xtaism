package xyz.aesthetical.xtaism.hack.hacks.render;

import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class Fullbright extends Hack {
	public Fullbright() {
		super(
			"Fullbright",
			Category.RENDER,
			-1,
			Integer.parseInt("ffc0cb", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			mc.gameSettings.gammaSetting = 100f;
		} else {
			mc.gameSettings.gammaSetting = 0f;
		}
	}
}
