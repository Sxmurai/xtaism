package xyz.aesthetical.xtaism.features.hacks.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayer.Position;
import net.minecraft.util.math.BlockPos;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;
import xyz.aesthetical.xtaism.features.gui.options.XtaismOptions.KeybindOpt;

@Hack(name = "Jesus", description = "Makes you walk on water", color = 11261429)
@Keybind(setting = KeybindOpt.KEYBIND_JESUS)
@Category(category = Group.MOVEMENT)
public class Jesus extends Mod {
	private int timer = 0;
	
	@Override
	public void onUpdate() {		
		Block under = mc.world.getBlockState(
			new BlockPos(mc.player.posX, mc.player.posY - 0.1, mc.player.posZ)
		).getBlock();

		if (under == Blocks.WATER) {
			mc.player.motionY = 0;
			//mc.player.onGround = true;
			//mc.player.connection.sendPacket(new CPacketPlayer(true));
		} 
	}
	
	@Override
	public boolean prePacketSent(Packet<?> pk) {
		if (!(pk instanceof CPacketPlayer)) {
			return false;
		}
		
		CPacketPlayer packet = (CPacketPlayer) pk;
		
		if (!(packet instanceof CPacketPlayer.Position) || !(packet instanceof CPacketPlayer.PositionRotation)) {
			return false;
		}
		
		if (mc.player.isInWater()) {
			return false;
		}
		
		if (mc.player.fallDistance > 3) {
			return false;
		}
		
		if (mc.player.movementInput == null) {
			return true;
		}
		
		++timer;
		if (timer < 4) {
			return false;
		}
		
		double[] pos = new double[] { packet.getX(0), packet.getY(0) - 0.05, packet.getZ(0) };
		
		if (packet instanceof CPacketPlayer.Position) {
			mc.player.connection.getNetworkManager().sendPacket(new CPacketPlayer.Position(pos[0], pos[1], pos[2], true));
		} else {
			mc.player.connection.getNetworkManager().sendPacket(
				new CPacketPlayer.PositionRotation(pos[0], pos[1], pos[2], packet.getYaw(0), packet.getPitch(0), true)
			);
		}
		
		return false;
	}
}
