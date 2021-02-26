package xyz.aesthetical.xtaism.hacks.combat;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.math.Vec3d;
import xyz.aesthetical.xtaism.entities.hacks.Group;
import xyz.aesthetical.xtaism.entities.hacks.Mod;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Category;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Hack;
import xyz.aesthetical.xtaism.entities.hacks.annotations.Keybind;

@Hack(name = "Auto Crystal", description = "Auto hits placed crystals", color = 16073282)
@Keybind(key = Keyboard.KEY_M)
@Category(category = Group.COMBAT)
public class AutoCrystal extends Mod {
	// TODO: make these settings
	private boolean onlyPlacedByPlayer = true;
	private boolean autoPlace = false;
	
	@Override
	public void onUpdate() {
		for (Entity entity : mc.world.loadedEntityList) {
			if (!(entity instanceof EntityEnderCrystal)) {
				continue;
			}
			
			if (entity.getDistanceSq(mc.player) < Math.pow(2, 2)) {
				continue;
			}
			
			float[] cam = getLook(mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity.getPositionVector());
			
			mc.player.rotationYaw = cam[0];
			mc.player.rotationPitch = cam[1];
			
			mc.player.connection.sendPacket(new CPacketUseEntity(entity));			
		}
	}
	
	private float[] getLook(Vec3d v, Vec3d e) {
		double dX = v.x - e.x;
		double dY = v.y - e.y;
		double dZ = v.z - e.z;
		
		double dXZ = Math.sqrt(dX * dX + dZ * dZ);
		
		float yaw = (float) Math.toDegrees(Math.atan2(dZ, dX)) + 90;
		float pitch = (float) Math.toDegrees(Math.atan2(dY, dXZ));
		
		return new float[] {yaw, pitch};
	}
}
