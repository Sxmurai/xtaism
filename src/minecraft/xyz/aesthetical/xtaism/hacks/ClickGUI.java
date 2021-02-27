package xyz.aesthetical.xtaism.hacks;

import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.gui.clickgui.Renderer;

@Hack(name = "ClickGUI", description = "The client's GUI", color = 16777215)
@Keybind(key = Keyboard.KEY_RSHIFT)
@Category(category = Group.HIDDEN)
public class ClickGUI extends Mod {
	@Override
	public void onEnable() {
		if (!(mc.currentScreen instanceof GuiManagerDisplayScreen)) {
			mc.displayGuiScreen(new GuiManagerDisplayScreen(mc.xtaism.gui));
			Renderer.renderAndUpdateFrames();
		}
	}
	
	@Override
	public void onUpdate() {
		if (!mc.isGamePaused()) {
			this.toggle();
		}
	}
}