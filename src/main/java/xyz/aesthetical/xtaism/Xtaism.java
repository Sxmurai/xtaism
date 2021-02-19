package xyz.aesthetical.xtaism;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import xyz.aesthetical.xtaism.gui.Manager;
import xyz.aesthetical.xtaism.hacks.movement.*;
import xyz.aesthetical.xtaism.hacks.render.*;
import xyz.aesthetical.xtaism.utils.XrayUtils;
import xyz.aesthetical.xtaism.hacks.Module;
import xyz.aesthetical.xtaism.hacks.hidden.*;

public class Xtaism {
	private ArrayList<Module> modules = new ArrayList();
	private Logger LOGGER = LogManager.getLogger();
	
	public Manager guiManager = new Manager();
	public boolean loaded = false;
	
	public void onLoaded() {
		LOGGER.info("Adding Modules...");
		
		// Render Hacks
		this.addModule(new Xray());
		this.addModule(new Fullbright());
		this.addModule(new MobESP());
		this.addModule(new ChestESP());
		
		// Movement Hacks
		this.addModule(new Flight());
		this.addModule(new Jesus());
		this.addModule(new Sprint());
		this.addModule(new Boatfly());
		
		// Miscellaneous
		this.addModule(new ClickGUI());
		
		LOGGER.info(String.format("Added %s modules! Loading GUI...", this.modules.size()));
		
		guiManager.setTheme(new SimpleTheme());
		guiManager.setup();
		
		LOGGER.info("GUI Loaded! Welcome to Xtasim!");
	}
	
	public void onKeyPressed(int key) {		
		for (Module mod : this.modules) {
			if (key == mod.getKey()) {
				mod.toggle();
			}
		}
	}
	
	public void onRender() {
		for (Module mod : this.modules) {
			mod.onRender();
		}
	}
	
	public void onUpdate() {
		for (Module mod : modules) {
			mod.onUpdate();
		}
	}
	
	public ArrayList<Module> getModules() {
		return this.modules;
	}
	
	private void addModule(Module mod) {
		modules.add(mod);
	}
	
	public boolean moduleIsEnabled(String name) {
		boolean isEnabled = false;
		
		for (Module mod : getModules()) {
			if (mod.getName() == name) {
				isEnabled = mod.isEnabled();
				return isEnabled;
			}
		}
		
		return isEnabled;
	}
}
