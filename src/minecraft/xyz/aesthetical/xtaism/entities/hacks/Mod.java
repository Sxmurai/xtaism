package xyz.aesthetical.xtaism.entities.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import xyz.aesthetical.xtaism.entities.Feature;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;

public abstract class Mod implements Feature {
	private boolean toggled = false;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public Hack getHackInfo() throws NullPointerException {
		return this.getClass().getAnnotation(Hack.class);
	}
	
	public Group getCategory() {
		return this.getClass().getAnnotation(Category.class).category();
	}
	
	public String getCategoryName() {
		String categoryName = getCategory().toString();
		return Character.toUpperCase(categoryName.charAt(0)) + categoryName.substring(1).toLowerCase();
	}
	
	public Keybind getKeyBindInfo() {
		return this.getClass().getAnnotation(Keybind.class);
	}
	
	public KeybindOpt getKeybindSetting() {
		return getKeyBindInfo().setting();
	}
	
	public void onEnable() {}
	public void onDisable() {}
	public void onUpdate() {}
	public void onRender() {}
	public boolean prePacketSent(Packet<?> pk) {
		return false;
	}
	
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
