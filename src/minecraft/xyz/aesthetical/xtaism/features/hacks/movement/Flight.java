package xyz.aesthetical.xtaism.features.hacks.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;

@Hack(name = "Flight", description = "Makes you fly", color = 11261429)
@Keybind(setting = KeybindOpt.KEYBIND_FLIGHT)
@Category(category = Group.MOVEMENT)
public class Flight extends Mod {
	// TODO: make this a setting
	private boolean useObvious = false;
	private float speed = .5f;
	
	@Override
	public void onUpdate() {
		if (useObvious) {
			mc.player.capabilities.isFlying = true;
			mc.player.capabilities.setFlySpeed(speed);
		} else {
			if (mc.gameSettings.keyBindJump.isKeyDown()) {
				mc.player.motionY += 0.2;
			}
			
			if (mc.gameSettings.keyBindSneak.isKeyDown()) {
				mc.player.motionY -= 0.2;
			}			
		}
	}
	
	@Override
	public void onDisable() {
		mc.player.capabilities.isFlying = false;
		mc.player.capabilities.setFlySpeed(.05f);
	}
}
