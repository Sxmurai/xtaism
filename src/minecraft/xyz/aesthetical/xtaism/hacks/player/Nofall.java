package xyz.aesthetical.xtaism.hacks.player;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.CPacketPlayer;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;


@Hack(name = "Nofall", description = "Makes you not take fall damage", color = 16073282)
@Keybind(key = Keyboard.KEY_NONE)
@Category(category = Group.PLAYER)
public class Nofall extends Mod {
	@Override
	public void onUpdate() {
		if (mc.player.fallDistance > 2) {
			mc.player.onGround = true;
			mc.player.connection.sendPacket(new CPacketPlayer(true));
		}
	}
}
