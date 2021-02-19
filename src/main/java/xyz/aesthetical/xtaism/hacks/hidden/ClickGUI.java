package xyz.aesthetical.xtaism.hacks.hidden;

import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.gui.Renderer;
import xyz.aesthetical.xtaism.hacks.Group;
import xyz.aesthetical.xtaism.hacks.Module;

public class ClickGUI extends Module {
	public ClickGUI() {
		super("Click GUI", Group.HIDDEN, Keyboard.KEY_TAB);
	}
	
	@Override
	public void onEnable() {
		if (!(minecraft.currentScreen instanceof GuiManagerDisplayScreen)) {
			minecraft.displayGuiScreen(new GuiManagerDisplayScreen(minecraft.xtaism.guiManager));
			Renderer.renderAndUpdateFrames();
		}
	}
	
	@Override
	public void onUpdate() {
		if (!minecraft.isGamePaused() && this.isEnabled()) {
			this.toggle();
		}
	}
}
