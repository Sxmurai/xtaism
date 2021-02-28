package xyz.aesthetical.xtaism.hacks.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(name = "Auto Sneak", description = "Makes you sneak on edges",color = 11261429)
@Keybind(key = Keyboard.KEY_NONE)
@Category(category = Group.MOVEMENT)
public class AutoSneak extends Mod {
	@Override
	public void onUpdate() {
		if (mc.gameSettings.keyBindJump.isKeyDown() && !mc.player.onGround) {
			return;
		}
		
		IBlockState xBlock = mc.world.getBlockState(new BlockPos(mc.player.posX + 0.03, mc.player.posY - 1, mc.player.posZ));
		IBlockState zBlock = mc.world.getBlockState(new BlockPos(mc.player.posX, mc.player.posY - 1, mc.player.posZ + 0.03));

		if (xBlock.getBlock() == Blocks.AIR || zBlock.getBlock() == Blocks.AIR) {
			setSneaking(true);
		} else {
			setSneaking(false);
		}
	}
	
	@Override
	public void onDisable() {
		if (mc.player.isSneaking()) {
			setSneaking(false);
		}
	}
	
	private void setSneaking(boolean s) {
		KeyBinding bind = mc.gameSettings.keyBindSneak;
		bind.setKeyBindState(bind.getKeyCode(), s);
	}
}
