package xyz.aesthetical.xtaism.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.darkstorm.minecraft.gui.AbstractGuiManager;
import org.darkstorm.minecraft.gui.component.Button;
import org.darkstorm.minecraft.gui.component.Component;
import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.component.basic.BasicButton;
import org.darkstorm.minecraft.gui.component.basic.BasicFrame;
import org.darkstorm.minecraft.gui.component.basic.BasicLabel;
import org.darkstorm.minecraft.gui.layout.GridLayoutManager;
import org.darkstorm.minecraft.gui.layout.GridLayoutManager.HorizontalGridConstraint;
import org.darkstorm.minecraft.gui.listener.ButtonListener;
import org.darkstorm.minecraft.gui.theme.Theme;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import xyz.aesthetical.xtaism.hacks.Group;
import xyz.aesthetical.xtaism.hacks.Module;

public final class Manager extends AbstractGuiManager {
	private final AtomicBoolean setup = new AtomicBoolean();
	
	public class ModuleFrame extends BasicFrame {
		private ModuleFrame() {}
		
		private ModuleFrame(String title) {
			this.setTitle(title);
		}
	}
	
	@Override
	public void setup() {
		final Map<Group, ModuleFrame> frames = new HashMap();
		
		for (Module mod : Minecraft.getMinecraft().xtaism.getModules()) {
			if (mod.getGroup().equals(Group.HIDDEN)) {
				continue;
			}
			
			ModuleFrame frame = frames.get(mod.getGroup());
			
			if (frame == null) {
				frame = new ModuleFrame(
					Character.toUpperCase(mod.getGroup().toString().charAt(0)) + 
					mod.getGroup().toString().substring(1).toLowerCase()
				);
								
				frame.setTheme(new SimpleTheme());
				frame.setLayoutManager(new GridLayoutManager(2, 0));
				frame.setVisible(true);
				frame.setClosable(false);
				frame.setMinimized(true);
				
				addFrame(frame);
				frames.put(mod.getGroup(), frame);
			}
			
			String fullName = mod.getKey() != -1 
				? mod.getName() + " [" + Keyboard.getKeyName(mod.getKey()).toUpperCase() + "]" 
				: mod.getName();
			
			frame.add(new BasicLabel(mod.getName()));
			final Module m = mod;
			
			Button button = new BasicButton(m.isEnabled() ? "Disable" : "Enable") {
				@Override
				public void update() {
					setText(m.isEnabled() ? "Disable" : "Enable");
				}
			};
			
			button.addButtonListener(new ButtonListener() {
				@Override
				public void onButtonPress(Button button) {
					m.toggle();
					button.setText(m.isEnabled() ? "Disable" : "Enable");
				}
			});
			
			frame.add(button, HorizontalGridConstraint.RIGHT);
		}
		
		resizeComponents();
		
		Minecraft mc = Minecraft.getMinecraft();
		Dimension maxSize = recalculateSizes();
		
		int offsetX = 5, offsetY = 5;
		int scale = mc.gameSettings.guiScale;
		
		if (scale == 0) {
			scale = 1000;			
		}
		
		int scaleFactor = 0;
		
		while (scaleFactor < scale && mc.displayWidth / (scaleFactor + 1) >= 320 && mc.displayHeight / (scaleFactor + 1) >= 240) {
			scaleFactor++;			
		}
		
		for (Frame frame : getFrames()) {
			frame.setX(offsetX);
			frame.setY(offsetY);
			
			offsetX += maxSize.width + 5;
			
			if (offsetX + maxSize.width + 5 > mc.displayWidth / scaleFactor) {
				offsetX = 5;
				offsetY += maxSize.height + 5;
			}
		}
	}

	@Override
	protected void resizeComponents() {
		Theme theme = getTheme();
		Frame[] frames = getFrames();
		Button enable = new BasicButton("Enable");
		Button disable = new BasicButton("Disable");
		
		Dimension enableSize = theme.getUIForComponent(enable).getDefaultSize(enable);
		Dimension disableSize = theme.getUIForComponent(disable).getDefaultSize(disable);
		
		int buttonWidth = Math.max(enableSize.width, disableSize.width);
		int buttonHeight = Math.max(enableSize.height, disableSize.height);
		
		for (Frame frame : frames) {
			if (frame instanceof ModuleFrame) {
				for (Component component : frame.getChildren()) {
					if (component instanceof Button) {
						component.setWidth(buttonWidth);
						component.setHeight(buttonHeight);
					}
				}
			}
		}
		
		recalculateSizes();
	}
	
	private Dimension recalculateSizes() {
		Frame[] frames = getFrames();
		int maxWidth = 0, maxHeight = 0;
		
		for (Frame frame : frames) {
			Dimension defaultDimension = frame.getTheme().getUIForComponent(frame).getDefaultSize(frame);
			maxWidth = Math.max(maxWidth, defaultDimension.width);
			frame.setHeight(defaultDimension.height);
			
			if (frame.isMinimized()) {
				for (Rectangle area : frame.getTheme().getUIForComponent(frame).getInteractableRegions(frame)) {
					maxHeight = Math.max(maxHeight, area.height);					
				}
			} else {
				maxHeight = Math.max(maxHeight, defaultDimension.height);				
			}
		}
		
		for (Frame frame : frames) {
			frame.setWidth(maxWidth);
			frame.layoutChildren();
		}
		
		return new Dimension(maxWidth, maxHeight);
	}
}
