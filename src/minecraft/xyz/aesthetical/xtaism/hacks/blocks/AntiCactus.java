package xyz.aesthetical.xtaism.hacks.blocks;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.Packet;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(name = "Anti Cactus", description = "Makes you take no damage to cactuses", color = 6155603)
@Keybind(key = Keyboard.KEY_C)
@Category(category = Group.BLOCKS)
public class AntiCactus extends Mod {
	// god i fucking hate cactuses, theyre so annoying cause if you daze out for one second they just
	// appear out of nowhere like wtf
	
	@Override
	public boolean prePacketSent(Packet<?> pk) {
		System.out.println(pk.getClass().getTypeName());
		return false;
	}
}
