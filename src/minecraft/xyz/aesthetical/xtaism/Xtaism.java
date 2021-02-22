package xyz.aesthetical.xtaism;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.gui.options.XtaismGUISettings;
import xyz.aesthetical.xtaism.gui.options.XtaismOptions;
import xyz.aesthetical.xtaism.hacks.movement.*;

public class Xtaism {
	protected ArrayList<Mod> hacks;
	public XtaismOptions settings;
	
	private Logger LOGGER = LogManager.getLogger(Xtaism.class);
	
	public void init() throws InstantiationException, IllegalAccessException, IOException {
		hacks = new ArrayList();
		settings = new XtaismOptions();
		
		// Add movement hacks	
		hacks.add(new AutoSprint());
		hacks.add(new BunnyHop());
				
		LOGGER.info("Loaded {} hacks", hacks.size());
		
		try {
			boolean wasSuccessful = this.settings.loadOptions();
	
			if (!wasSuccessful) {
				new File(Minecraft.getMinecraft().gameDir, "optionsXtaism.txt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleKeyPressed(int key) {
		for (Mod hack : hacks) {
			if (hack.getKey() == key) {
				hack.toggle();
			}
		}
	}
	
	public void handleUpdate() {
		for (Mod hack : hacks) {
			if (hack.isToggled()) {
				hack.onUpdate();				
			}
		}
	}
	
	public void handleRender() {
		for (Mod hack : hacks) {
			if (hack.isToggled()) {
				hack.onRender();				
			}
		}
	}
	
	public ArrayList<Mod> getHacks() {
		return hacks;
	}
	
	public boolean isHackEnabled(String name) {
		for (Mod hack : hacks) {
			if (hack.getName() == name && hack.isToggled()) {
				return true;
			}
		}
		
		return false;
	}
}
