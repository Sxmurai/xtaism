package xyz.aesthetical.xtaism.hack.hacks.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import xyz.aesthetical.xtaism.hack.Category;
import xyz.aesthetical.xtaism.hack.Hack;

public class AutoSneak extends Hack {	
	public boolean sneakingByHack = false;
	
	public AutoSneak() {
		super(
			"Auto Sneak",
			Category.MOVEMENT,
			-1,
			Integer.parseInt("3377de", 16)
		);
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if (mc.xtaism.isHackEnabled("Bunnyhop")) {
				return;
			}
						
			Vec3d playerPos = mc.player.getPositionVector();
			
			Block nextBlockRight = mc.world.getBlockState(new BlockPos(playerPos.x + 0.2, playerPos.y - 1, playerPos.z)).getBlock();
			Block nextBlockLeft = mc.world.getBlockState(new BlockPos(playerPos.x, playerPos.y - 1, playerPos.z + 0.2)).getBlock();
									
			if (nextBlockRight instanceof BlockAir || nextBlockLeft instanceof BlockAir) {
				if (!mc.player.movementInput.sneak) {
					sneakingByHack = true;
					setSneaking(true);
				}
			} else {
				if (sneakingByHack) {
					setSneaking(false);		
					sneakingByHack = false;
					return;
				}
				
				setSneaking(mc.player.isSneaking());
			}
		}
	}
	
	private void setSneaking(boolean shouldSneak) {
		KeyBinding bind = mc.gameSettings.keyBindSneak;
		
		bind.setKeyBindState(bind.getKeyCode(), shouldSneak);
	}
}
