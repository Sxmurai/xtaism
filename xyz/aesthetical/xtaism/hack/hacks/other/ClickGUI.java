package xyz.aesthetical.xtaism.hack.hacks.other;

import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.gui.Renderer;
import xyz.aesthetical.xtaism.hack.Hack;

public class ClickGUI extends Hack {
	public ClickGUI() {
		super(
			"ClickGUI",
			null,
			Keyboard.KEY_RSHIFT,
			-1
		);
	}
	
	@Override
	public void onEnable() {
		if (!(mc.currentScreen instanceof GuiManagerDisplayScreen)) {
			mc.displayGuiScreen(new GuiManagerDisplayScreen(mc.xtaism.guiManager));
			Renderer.renderAndUpdateFrames();
		}
	}
	
	@Override
	public void onUpdate() {
		if (!mc.isGamePaused() && this.isToggled()) {
			this.toggle();
		}
	}
}
