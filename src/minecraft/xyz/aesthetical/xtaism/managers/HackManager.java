package xyz.aesthetical.xtaism.managers;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.hacks.ClickGUI;
import xyz.aesthetical.xtaism.hacks.blocks.*;
import xyz.aesthetical.xtaism.hacks.combat.*;
import xyz.aesthetical.xtaism.hacks.movement.*;
import xyz.aesthetical.xtaism.hacks.other.*;
import xyz.aesthetical.xtaism.hacks.player.*;
import xyz.aesthetical.xtaism.hacks.render.*;

public class HackManager {
	protected ArrayList<Mod> modules = new ArrayList();
	protected Logger LOGGER = LogManager.getLogger(HackManager.class);
	
	public void load() {		
		// Add movement modules	
		modules.add(new AutoSneak());
		modules.add(new AutoSprint());
		modules.add(new Blink());
		modules.add(new BunnyHop());
		modules.add(new Flight());
		modules.add(new Jesus());
		modules.add(new Spider());
		
		// Add render
		modules.add(new ChestESP());
		modules.add(new FreeCam());
		modules.add(new Fullbright());
		modules.add(new PlayerESP());
		
		// Add block related modules
		modules.add(new AntiCactus());
		
		// Add combat modules
		modules.add(new AutoCrystal());
		modules.add(new AutoTotem());
		modules.add(new Criticals());
		modules.add(new KillAura());
		
		// Add player modules
		modules.add(new AutoRespawn());
		modules.add(new Nofall());
		
		// Add other modules
		modules.add(new ClickGUI());
		
		modules.add(new FancyChat());
		
		LOGGER.info("Loaded {} modules", modules.size());
	}
	
	public void handleKeyPressed(int key) {
		for (Mod hack : modules) {
			if (hack.getKey() == key) {
				hack.toggle();
			}
		}
	}
	
	public void handleUpdate() {
		for (Mod hack : modules) {
			hack.mc = Minecraft.getMinecraft();
			
			if (hack.isToggled()) {
				hack.onUpdate();				
			}
		}
	}
	
	public void handleRender() {
		for (Mod hack : modules) {
			if (hack.isToggled()) {
				hack.onRender();				
			}
		}
	}
	
	public ArrayList<Mod> getHacks() {
		return modules;
	}
	
	@Nullable
	public Mod getHack(Class<? extends Mod> clazz) {
		for (Mod hack : modules) {
			if (hack.getClass() == clazz) {
				return hack;
			}
		}
		
		return null;
	}
	
	@Nullable
	public Mod getHack(String name) {
		for (Mod hack : modules) {
			if (hack.getName().replaceAll(" ", "").equalsIgnoreCase(name)) {
				return hack;
			}
		}
		
		return null;
	}
	
	public boolean isHackEnabled(String name) {
		for (Mod hack : modules) {
			if (hack.getName() == name && hack.isToggled()) {
				return true;
			}
		}
		
		return false;
	}
}

