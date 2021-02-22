package xyz.aesthetical.xtaism;

import java.util.ArrayList;
import java.util.Set;

import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import xyz.aesthetical.xtaism.gui.Manager;
import xyz.aesthetical.xtaism.hack.Hack;
import xyz.aesthetical.xtaism.hack.hacks.combat.*;
import xyz.aesthetical.xtaism.hack.hacks.movement.*;
import xyz.aesthetical.xtaism.hack.hacks.other.ClickGUI;
import xyz.aesthetical.xtaism.hack.hacks.player.*;
import xyz.aesthetical.xtaism.hack.hacks.render.*;

public class Xtaism {
	private final ArrayList<Hack> hacks = new ArrayList();
	public Manager guiManager = new Manager();
	
	public void init() {
		// The ClickGUI
		this.addHack(new ClickGUI());
		
		// Movement
		this.addHack(new AutoSprint());
		this.addHack(new AutoWalk());
		this.addHack(new AutoSneak());
		this.addHack(new Bunnyhop());
		this.addHack(new BoatFly());
		this.addHack(new EntitySpeed());
		this.addHack(new Jesus());
		this.addHack(new Jetpack());
		this.addHack(new Speed());
		this.addHack(new Spider());
		
		// Rendering
		this.addHack(new MobESP());
		this.addHack(new ChestESP());
		this.addHack(new Flight());
		this.addHack(new Fullbright());
		this.addHack(new PlayerESP());
		this.addHack(new Xray());
		
		// Combat
		this.addHack(new KillAura());
		
		// Player
		this.addHack(new AntiCactus());
		this.addHack(new AutoRespawn());
		this.addHack(new Nofall());
		
		System.out.println(String.format("Loaded %s hacks", this.hacks.size()));
		
		guiManager.setTheme(new SimpleTheme());
		guiManager.setup();
	}
	
	public void onKeyPress(int key) {		
		for (Hack hack : hacks) {
			if (hack.getKey() == key) {
				hack.toggle();
			}
		}
	}
	
	public void onUpdate() {
		for (Hack hack : hacks) {
			hack.onUpdate();
		}
	}
	
	public void onRender(double partialTicks) {
		for (Hack hack : hacks) {
			hack.onRender(partialTicks);
		}
	}
	
	private void addHack(Hack hack) {
		this.hacks.add(hack);
	}
	
	public ArrayList<Hack> getAllHacks() {
		return hacks;
	}
	
	public boolean isHackEnabled(String name) {		
		for (Hack h : getAllHacks()) {
			if (h.getName() == name && h.isToggled()) {
				return true;
			}
		}
		
		return false;
	}
	
	public Hack getHackByName(String name) {
		for (Hack h : getAllHacks()) {
			if (h.getName() == name) {
				return h;
			}
		}
		
		return null;
	}
}
