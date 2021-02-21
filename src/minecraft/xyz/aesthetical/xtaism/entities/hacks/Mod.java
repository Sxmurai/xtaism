package xyz.aesthetical.xtaism.entities.hacks;

import net.minecraft.client.Minecraft;
import xyz.aesthetical.xtaism.entities.Module;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

public abstract class Mod implements Module {
	private boolean toggled = false;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public Hack getHackInfo() throws NullPointerException {
		return this.getClass().getAnnotation(Hack.class);
	}
	
	public Group getCategory() throws NullPointerException {
		return this.getClass().getAnnotation(Category.class).category();
	}
	
	public int getKey() throws NullPointerException {
		return this.getClass().getAnnotation(Keybind.class).key();
	}
	
	public void onEnable() {}
	public void onDisable() {}
	public void onUpdate() {}
	public void onRender() {}
	
	public boolean isToggled() {
		return toggled;
	}
	
	public void toggle() {
		toggled = !toggled;
		
		if (toggled) {
			onEnable();
		} else {
			onDisable();
		}
	}
	
	public String getName() {
		return getHackInfo().name();
	}
	
	public String getDescription() {
		return getHackInfo().description();
	}
	
	public int getColor() {
		return getHackInfo().color();
	}
}
