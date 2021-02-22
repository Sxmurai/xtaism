package xyz.aesthetical.xtaism.hack.hacks.render;

import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;
import xyz.aesthetical.xtaism.helpers.XrayHelper;

public class Xray extends Hack {		
	public Xray() {
		super(
			"Xray",
			Category.RENDER,
			Keyboard.KEY_X,
			Integer.parseInt("ffc0cb", 16)
		);
		
		XrayHelper.init();
	}
	
	@Override
	public void onEnable() {
		XrayHelper.toggle();
				
		mc.renderGlobal.loadRenderers();
	}
	
	@Override
	public void onDisable() {
		XrayHelper.toggle();
		
		mc.renderGlobal.loadRenderers();
	}
}
