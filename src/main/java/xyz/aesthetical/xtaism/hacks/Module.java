package xyz.aesthetical.xtaism.hacks;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;

public class Module {
	// Get the Minecraft Instance
	protected Minecraft minecraft = Minecraft.getMinecraft();
	
	//
	private final Logger LOGGER = LogManager.getLogger();
	
	// The name of the module and shit
	private String name;
	
	// The Group this is in, or the category. Defaulted as NONE
	private Group group = Group.NONE;
	
	// The modules' key
	private int key;
	
	// The color that will show up when it's enabled
	private int color = 0x00ff00;
	
	// If the module is enabled, false by default.
	private boolean enabled = false;
	
	public Module(String name, Group group, int key, int color) {
		this.name = name;
		this.group = group;
		this.key = key;			
		this.color = color;
	}
	
	// Set the module with a group
	public Module(String name, Group group, int key) {
		this.name = name;
		this.group = group;
		this.key = key;
	}
	
	// Set the module without a group.
	public Module(String name, int key) {
		this.name = name;
		this.key = key;
	}
	
	public Module(String name, Group group) {
		this.name = name;
		this.group = group;
	}
	
	public void onKeyPressed(int key) {}
	public void onRender() {}
	public void onUpdate() {}
	
	public void onEnable() {}
	public void onDisable() {}
	
	// Toggle the module
	public void toggle() {
		this.enabled = !this.enabled;
		
		if (this.enabled) {
			onEnable();
		} else {
			onDisable();
;		}
		
		LOGGER.info(String.format("%s %s", this.enabled == true ? "Enabled" : "Disabled", this.name));
	}
	
	// Tells us if the module is enabled.
	public boolean isEnabled() {
		return enabled;
	}
	
	public int getKey() {
		return key;
	}
	
	// Get this modules' name.
	public String getName() {
		return name;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public int getColor() {
		return color;
	}
}
