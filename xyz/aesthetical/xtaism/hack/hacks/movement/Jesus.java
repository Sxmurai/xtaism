package xyz.aesthetical.xtaism.hack.hacks.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class Jesus extends Hack {
	public Jesus() {
		super(
			"Jesus",
			Category.MOVEMENT,
			Keyboard.KEY_J,
			Integer.parseInt("3377de", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if (!mc.player.isOverWater() || !mc.player.isInWater() || mc.player.isSneaking()) {
				return;
			}
			
			if (mc.player.isInWater()) {
				mc.player.setVelocity(mc.player.motionX, 0.17, mc.player.motionZ);				
			}		
		}
	}
}
