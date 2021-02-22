package xyz.aesthetical.xtaism.hack.hacks.player;

import net.minecraft.network.play.client.CPacketPlayer;

import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class Nofall extends Hack {
	public Nofall() {
		super(
			"No Fall",
			Category.PLAYER,
			-1,
			Integer.parseInt("e3d022", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if (mc.player.fallDistance > 2f) {
				mc.player.connection.sendPacket(new CPacketPlayer(true));
			}
		}
		
		super.onUpdate();
	}
}
