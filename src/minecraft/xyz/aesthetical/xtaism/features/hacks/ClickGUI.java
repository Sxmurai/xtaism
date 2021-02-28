package xyz.aesthetical.xtaism.features.hacks;

import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.input.Keyboard;

import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.features.gui.clickgui.Renderer;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;;

@Hack(name = "ClickGUI", description = "The client's GUI", color = 16777215)
@Keybind(setting = KeybindOpt.KEYBIND_CLICKGUI)
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