package xyz.aesthetical.xtaism.hacks.render;

import org.lwjgl.input.Keyboard;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.aesthetical.xtaism.hacks.Group;
import xyz.aesthetical.xtaism.hacks.Module;

public class Fullbright extends Module {
	public Fullbright() {
		super(
			"Fullbright", 
			Group.RENDER, 
			Keyboard.KEY_C,
			Integer.parseInt("ffc0cb", 16)
		);
	}
	
	public void onUpdate() {
		if (this.isEnabled()) {
			minecraft.gameSettings.gammaSetting = 100f;
		} else {
			minecraft.gameSettings.gammaSetting = 0f;
		}
	}
}
