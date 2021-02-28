package xyz.aesthetical.xtaism.features.hacks.blocks;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.Packet;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;

@Hack(name = "Anti Cactus", description = "Makes you take no damage to cactuses", color = 6155603)
@Keybind(setting = KeybindOpt.KEYBIND_ANTICACTUS)
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
