package xyz.aesthetical.xtaism.gui.clickgui;

import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;

import net.minecraft.client.Minecraft;

public class Renderer {
	public static void renderAndUpdateFrames() {
		for (Frame frame : Minecraft.getMinecraft().xtaism.gui.getFrames()) {
			frame.update();
			
			if (frame.isPinned() || Minecraft.getMinecraft().currentScreen instanceof GuiManagerDisplayScreen) {
				frame.render();
			}
		}
	}
}