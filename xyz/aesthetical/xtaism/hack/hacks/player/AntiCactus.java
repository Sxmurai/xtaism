package xyz.aesthetical.xtaism.hack.hacks.player;

import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class AntiCactus extends Hack {
	public AntiCactus() {
		super(
			"AntiCactus",
			Category.PLAYER,
			-1,
			Integer.parseInt("e3d022", 16)
		);
	}
	
	@Override
	public void onEnable() {
		mc.renderGlobal.loadRenderers();
	}
	
	@Override
	public void onDisable() {
		mc.renderGlobal.loadRenderers();
	}
}
