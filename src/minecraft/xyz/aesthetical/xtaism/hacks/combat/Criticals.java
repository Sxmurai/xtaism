package xyz.aesthetical.xtaism.hacks.combat;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(
	name = "Criticals", 
	description = "Gives you a better chance at dealing critical hits",
	color = 16073282
)
@Keybind(key = Keyboard.KEY_B)
@Category(category = Group.COMBAT)
public class Criticals extends Mod {
	@Override
	public boolean prePacketSent(Packet<?> pk) {
		if (!(pk instanceof CPacketUseEntity)) {
			return false;
		}
		
		mc.player.jump();
		
		return false;
	}
}
