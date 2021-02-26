package xyz.aesthetical.xtaism.hacks.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;


@Hack(name = "Jesus", description = "Makes you walk on water", color = 11261429)
@Keybind(key = Keyboard.KEY_J)
@Category(category = Group.MOVEMENT)
public class Jesus extends Mod {
	@Override
	public void onUpdate() {		
		// TODO: add setting to allow horses to also walk on water
		if (mc.player.isRidingHorse()) {
			Entity horse = mc.player.getRidingEntity();
			
			if (horse.isInWater()) {
				// make horse go up if its swimming in water
				horse.setVelocity(horse.motionX, horse.motionY + 0.1, horse.motionZ);
				return;
			}
			
			Block blockUnderHorse = mc.world.getBlockState(new BlockPos(horse.posX, horse.posY - 0.1, horse.posZ)).getBlock();
			
			if (blockUnderHorse == Blocks.WATER) {
				horse.motionY = 0;
				horse.onGround = true;	
				
				mc.player.connection.sendPacket(new CPacketPlayer(true));
			}
		} else {
			if (mc.player.isInWater()) {
				// make player go up if its swimming in water
				mc.player.setVelocity(mc.player.motionX, mc.player.motionY + 0.1, mc.player.motionZ);
				return;
			}
			
			Block blockUnder = mc.world.getBlockState(new BlockPos(mc.player.posX, mc.player.posY - 0.1, mc.player.posZ)).getBlock();
			
			if (blockUnder == Blocks.WATER) {
				mc.player.motionY = 0;
				mc.player.onGround = true;
				
				mc.player.connection.sendPacket(new CPacketPlayer(true));
			}
		}
	}
}
