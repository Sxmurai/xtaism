package xyz.aesthetical.xtaism.hack;

import java.util.ArrayList;

import org.darkstorm.minecraft.gui.component.AbstractComponent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class Hack {
	private String name;
	private int key;
	private int color;
	private Category category;
	
	private boolean enabled = false;
	
	public Minecraft mc = Minecraft.getMinecraft();

	public ArrayList<AbstractComponent> settings = new ArrayList();
	public boolean settingsMenuOpen = false;
	
	public Hack(
		String name,
		Category category,
		int key,
		int color
	) {
		this.name = name;
		this.key = key;
		this.category = category;
		this.color = color;
	}
	
	public void onEnable() {}
	public void onDisable() {}
	public void onRender(double partialTick) {}
	public void onUpdate() {}
	
	public void toggle() {
		this.enabled = !this.enabled;
		
		if (this.enabled) {
			System.out.println(String.format("Enabled %s", this.name));
			this.onEnable();
		} else {
			System.out.println(String.format("Disabled %s", this.name));
			this.onDisable();
		}
	}
	
	public void addSetting(AbstractComponent setting) {
		settings.add(setting);
	}
	
	public boolean isToggled() {
		return this.enabled == true;
	}
	
	public String getName() {
		return name;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public String getCategoryName() {
		String name = category.toString();
		
		return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
	}
	
	public int getColor() {
		return color;
	}
	
	public int getKey() {
		return key;
	}
}
