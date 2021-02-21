package xyz.aesthetical.xtaism;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;

import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.hacks.movement.*;

public class Xtaism {
	protected ArrayList<Mod> hacks;
	protected Reflections reflections;
	private Logger LOGGER = LogManager.getLogger(Xtaism.class);
	
	public void init() throws InstantiationException, IllegalAccessException {
		hacks = new ArrayList();
		
		// Add movement hacks	
		hacks.add(new AutoSprint());
				
		LOGGER.info("Loaded {} hacks", hacks.size());
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
			hack.onUpdate();
		}
	}
	
	public void handleRender() {
		for (Mod hack : hacks) {
			hack.onRender();
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
